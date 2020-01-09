package com.meng.example.snake.gameengine;

import com.meng.example.snake.listener.SnakeGameListener;
import com.meng.example.snake.model.*;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * 贪吃蛇游戏引擎
 */
public class SnakeGameEngine {
    static final Logger logger = LoggerFactory.getLogger(SnakeGameEngine.class);

    public Map<String, SnakeEntity> snakes = new HashMap<>();
    private final int mapWidth;
    private final int mapHeight;
    // 存储地图上所有节点
    private final Mark mapsMarks[];
    //刷新时间
    private final int refreshTime;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    private ScheduledExecutorService stateService = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> mapFutrue;
    private SnakeGameListener listener;

    private Long currentVersion = 0L;
    private volatile LinkedList<VersionData> historyVersionData = new LinkedList<>();
    private volatile VersionData currentMapData = null;
    //最多保存的历史版本
    private static final int historyVersionMax = 20;

    private ArrayList<Food> foods = new ArrayList<Food>();
    private int foodMaxSize = 10;

    private ScheduledFuture<?> stateFuture;

    //事件队列
    private List<GameEvent> eventQueue = Collections.synchronizedList(new LinkedList<GameEvent>());


    public SnakeGameEngine(){
        this.mapWidth = 400;
        this.mapHeight = 300;
        refreshTime = 200;
        mapsMarks = new Mark[mapWidth * mapHeight];
    }

    public SnakeGameEngine(int mapWidth, int mapHeight, int refreshTime) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.refreshTime = refreshTime;
        mapsMarks = new Mark[mapWidth * mapHeight];
    }

    /**
     * 启动游戏
     */
    public void start(){
        logger.info("游戏引擎启动...");
        mapFutrue = executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                //蛇定时运动
                gameTimeStep();
            }
        }, refreshTime, refreshTime, TimeUnit.MILLISECONDS);

        //状态变更信息
        stateFuture = stateService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                //触发状态变更事件
                fireStateChange();
                fireNoticeEvent();
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);

    }

    private void fireNoticeEvent(){
        if(listener == null){
            return;
        }
        if(eventQueue.isEmpty()){
            return;
        }
        GameEvent[] events = eventQueue.toArray(new GameEvent[eventQueue.size()]);
        listener.noticeEvent(events);
        for (GameEvent event : events) {
            eventQueue.remove(event);
        }
    }


    /**
     * 触发游戏统计计算
     */
    private void fireStateChange(){
        if(listener != null){
            GameStatistic statistic = new GameStatistic();
            statistic.setLastVersion(currentVersion);
            // TODO 在线人数需除去离线角色
            statistic.setOnlineCount(snakes.size());
            statistic.setRankingList(getRankingList());
            listener.statusChange(statistic);
        }
    }

    /**
     * 获取积分榜 前10位
     *
     * @return
     */
    private List<IntegralInfo> getRankingList(){
        List<IntegralInfo> result = new ArrayList<>(10);
        List<SnakeEntity> list = new ArrayList<>(snakes.size());
        list.addAll(snakes.values());
        Collections.sort(list, new Comparator<SnakeEntity>() {
            @Override
            public int compare(SnakeEntity o1, SnakeEntity o2) {
                return o1.getKillIntegral() - o2.getKillIntegral();
            }
        });
        IntegralInfo info;
        for (int i = 0; i < list.size() && i < 10; i++) {
            info = new IntegralInfo();
            info.setLastVersion(currentVersion);
            info.setGameName(list.get(i).getGameName());
            info.setAccountId(list.get(i).getAccountId());
            info.setDieIntegral(list.get(i).getDieIntegral());
            info.setKillIntegral(list.get(i).getKillIntegral());
            result.add(info);
        }
        return result;
    }

    private void afterBuild() {
        for (SnakeEntity snake : snakes.values()) {
            snake.flush();
        }
    }

    /**
     * 定时刷新地图
     */
    public void gameTimeStep(){
        try {
            build();
        }catch (Throwable e){
            logger.error("地图构建异常", e);
        }finally {
            afterBuild();
        }
    }

    /**
     * 构建当前所有蛇信息
     */
    private void build(){
        // 基于状态执行算法
        for(SnakeEntity snake : snakes.values()){
            switch (snake.getState()){
                case INACTIVE:
                    logger.info("激活新角色:{}当前版本:{}", snake.toString(), currentVersion + 1);
                    snake.active();
                    break;
                case ALIVE:
                    snake.moveStep();
                    break;
                case GROW:
                    // TODO 需重构成 先进食后消化
                    snake.addLineToHead();
                    snake.active();
                    //  增涨后恢复为普通状态
                    //  snake.moveStep();
                    break;
                case DYING:
                    snake.die();
                    GameEvent event = new GameEvent(GameEvent.EventType.DIE, "角色死亡");
                    event.setAccountId(snake.getAccountId());
                    eventQueue.add(event);
                    break;
                case DIE:
                    break;
                case OFFLINE:
                    break;
            }
        }
        // 当前版本新增的节点
        ArrayList<Integer[]> changeNodes = new ArrayList<>();
        //执行游戏出发的规则
        for(SnakeEntity snake : snakes.values()){
            if(snake.isOffline()){
                continue;
            }
            //判断蛇头是否撞击界面
            if(!snake.isDie() && !isMapRange(snake.getHead())){
                snake.dying();
            }
            for(Integer[] node : snake.getAddNodes()){
                //判断是否撞击了蛇身
                if(getMark(node).snakeNodes > 0){
                    //触发死亡规则
                    snake.dying();
                    //触发击杀规则
                    killSnake(snake, node);
                } else if(getMark(node).foodNode > 0){
                    //吃掉食物
                    digestionFood(snake, node);
                }
            }
            changeNodes.addAll(snake.getAddNodes());
            changeNodes.addAll(snake.getRemoveNodes());
        }

        //投放规定量食物
        while (foods.size() < foodMaxSize){
            Food food = grantFood();
            changeNodes.add(food.point);
        }

        // 如果变更不为null,则创建新的版本号
        if(!changeNodes.isEmpty()){
            long newVersion = currentVersion + 1;
            while (historyVersionData.size() >= historyVersionMax){
                historyVersionData.removeLast();
            }
            VersionData changData = encodeVersion(newVersion, changeNodes);
            historyVersionData.addFirst(changData);
            //变更版本号
            currentVersion = newVersion;
            //刷新整个地图
            getCurrentMapData(true);
            // 通知版本变更
            if(listener != null){
                try {
                    listener.versionChange(changData, null);
                }catch (Exception e){
                    logger.error("版本变更通知失败", e);
                }
            }
        }
    }

    //获取蛇头 位置
    public DrawingCommand getDrawingCommand(String accountId){
        DrawingCommand cmd = null;
        if (!snakes.containsKey(accountId)) {
            return cmd;
        }
        SnakeEntity snake = snakes.get(accountId);
        Integer[] head = snake.getHead();
        if (head != null){
            cmd = new DrawingCommand("Lime", head[1] + "," + head[0]);
        }
        return cmd;
    }


    /**
     * 蛇被击杀
     * @param snakeDie
     * @param killPoint
     */
    private void killSnake(SnakeEntity snakeDie, Integer[] killPoint){
        //找出击杀点所有角色
        List<SnakeEntity> list = getSnakeByNode(killPoint);
        //移除被击杀的对象
        list.remove(snakeDie);
        SnakeEntity killer = null;
        for(SnakeEntity snakeEntity : list){
            if(Arrays.equals(killPoint, snakeEntity.getHead())){
                continue;
            }
            killer = snakeEntity;
            break;
        }
        if(killer != null){
            killer.addKillIntegral();
            logger.info("{}击杀{}", killer.getGameName(), snakeDie.getGameName());
        }
    }

    /**
     * 吃掉食物
     * @param snake
     * @param point
     * @return
     */
    private Food digestionFood(SnakeEntity snake, Integer[] point){
        Food food = null;
        for(Food f : foods){
            if(Arrays.equals(f.point, point)){
                food = f;
                break;
            }
        }
        if(food == null){
            throw new RuntimeException(
                    String.format("消化食物异常，坐标上不存在指定食物x:%s,y:%s", point[1], point[0]));
        }
        foods.remove(food);
        getMark(point).foodNode = 0;
        snake.grow();
        logger.info("吃掉食物 位置信息：x={},y={},角色信息:{}", point[1], point[0], snake.toString());
        return food;
    }


    /**
     * 随机生成食物
     * @return
     */
    public Food grantFood(){
        //随机生成的投放点
        int releasePoint = -1;
        Random random = new Random();
        int start = random.nextInt(mapHeight * mapWidth - 5) + 4;
        int nextCount = random.nextInt(50);
        for(int i = start, n=0, m =0; i<mapsMarks.length && m< mapsMarks.length; i++,m ++){
            if(mapsMarks[i] == null || mapsMarks[i].isEmpty()){
                n++;
                releasePoint = i;
                if(n >=nextCount){
                    break;
                }
            }
            if(i == mapsMarks.length - 1){
                i = 0;
            }
        }
        if(releasePoint > -1){
            Integer[] point = new Integer[]{releasePoint/mapWidth, releasePoint%mapWidth};
            Food food = new Food(point, 1);
            foods.add(food);
            getMark(point).foodNode = 1;
            return food;
        } else {
            throw new RuntimeException("投食失败。无法找到空位投食");
        }

    }

    public List<VersionData> getVersion(Long[] versionId){
        List<VersionData> list = new ArrayList<>();
        VersionData[] historys = historyVersionData.toArray(new VersionData[list.size()]);
        for (VersionData historyVersion : historys) {
            for (long v : versionId) {
                if (historyVersion.getVersion() == v) {
                    list.add(historyVersion);
                }
            }
        }
        return list;
    }

    public VersionData getCurrentMapData(boolean check){
        if(currentMapData == null){
            currentMapData = encodeCurrentMapData();
        } else if(check && currentMapData.getVersion() < currentVersion){
            currentMapData = encodeCurrentMapData();
        }
        return currentMapData;
    }

    /**
     * 构建当前地图所有的像素
     * @return
     */
    private VersionData encodeCurrentMapData(){
        StringBuilder body = new StringBuilder();
        StringBuilder food = new StringBuilder();
        Mark mark;
        int x,y;
        for(int i=0; i<  mapsMarks.length; i++){
            mark = mapsMarks[i];
            x = i % mapWidth;
            y = i / mapWidth;
            if(mark != null && mark.snakeNodes > 0){
                body.append("," + x + "," + y);
            } else if (mark != null && mark.foodNode > 0) {
                food.append("," + x + "," + y);
            }
        }
        List<String> cmds = new ArrayList();
        List<String> cmdsDatas = new ArrayList();
        // 去掉前缀 中的逗号
        if (body.length() > 0) {
            body.deleteCharAt(0);
            cmds.add("Green");
            cmdsDatas.add(body.toString());
        }
        if (food.length() > 0) {
            food.deleteCharAt(0);
            cmds.add("Yellow");
            cmdsDatas.add(food.toString());
        }
        VersionData vd = new VersionData(currentVersion, System.currentTimeMillis());
        vd.setCmds(cmds.toArray(new String[cmds.size()]));
        vd.setCmdDatas(cmdsDatas.toArray(new String[cmdsDatas.size()]));
        vd.setFull(true);
        return vd;
    }



    /**
     * 根据 节点获取蛇
     * @param node
     * @return
     */
    private List<SnakeEntity> getSnakeByNode(Integer[] node){
        List<SnakeEntity> result = new ArrayList<>();
        for (SnakeEntity snakeEntity: snakes.values()){
            for (Integer[] integers : snakeEntity.getBodys()) {
                if (Arrays.equals(integers, node)) {
                    result.add(snakeEntity);
                    break;
                }
            }
        }
        return result;
    }



    //TODO bug 出生点 可能已经被占用
    public SnakeEntity newSnake(String accountId, String accountName) {
        int max = Math.min(mapHeight, mapWidth) - 10;
        int min = 10;
        Random random = new Random();
        //随机生成 出生点
        int startPoint = random.nextInt(max - min + 1) + min;
        SnakeEntity node = new SnakeEntity(this, accountId, startPoint, 3, SnakeDirection.RIGHT);
        String gameName = accountName;
        // 防重名机制,补充accountId为后缀
        for (SnakeEntity snakeEntity : snakes.values()) {
            if(accountName.equals(snakeEntity.getGameName())){
                gameName=gameName+"_"+accountId;
                break;
            }
        }
        node.setGameName(gameName);
        snakes.put(node.getAccountId(), node);
        this.logger.info("新增Snake ID:{} 出生点位:{} 初始节点:{}", accountId, startPoint, 3);
        return node;
    }

    //TODO BUG 复活点位 可能已经被占用
    public void doResurgence(String accountId){
        if(!snakes.containsKey(accountId)){
            this.logger.warn("角色复活失败，找不到指定帐户 ID:{}", accountId);
            return ;
        }
        if (!snakes.get(accountId).isDie()) {
            this.logger.warn("角色复活失败，必须为死亡状态 ID:{}", accountId);
            return;
        }
        int max = Math.min(mapWidth, mapHeight) - 10;
        int min = 10;
        Random random = new Random();
        // 随机生成 出生点位
        int startPoint = random.nextInt(max - min + 1) + min;
        snakes.get(accountId).resurgence(startPoint,3);
        this.logger.info("角色复活 ID:{} 出生点位:{} 初始节点:{}", accountId, startPoint, 3);
    }


    /**
     * 改变蛇的方向
     * @param accountId
     * @param controlCode
     */
    public void controlSnake(String accountId, int controlCode){
        if(!snakes.containsKey(accountId)){
            return;
        }
        SnakeEntity snake = snakes.get(accountId);
        if(snake.isDie()){
            return;
        }
        switch (controlCode){
            case SnakeDirection.LEFTNUM:
                snake.setDirection(SnakeDirection.LEFT);
                break;
            case SnakeDirection.UPNUM:
                snake.setDirection(SnakeDirection.UP);
                break;
            case SnakeDirection.RIGHTNUM:
                snake.setDirection(SnakeDirection.RIGHT);
                break;
            case SnakeDirection.DOWNNUM:
                snake.setDirection(SnakeDirection.DOWN);
                break;
        }
        logger.debug("指令控制 ID:{},指令:{}", accountId, controlCode);
    }


    public boolean isMapRange(Integer[] point) {
        return point[0] >=0 && point[0]< mapHeight && point[1] >=0 && point[1] < mapWidth;
    }

    private Mark getMark(int index){
        if(mapsMarks[index] == null){
            mapsMarks[index] = new Mark();
        }
        return mapsMarks[index];
    }

    public Mark getMark(Integer[] point){
        int index = point[0] * mapWidth + point[1];
        if(index <0 || index >= mapsMarks.length){
            throw new IllegalArgumentException(String
                    .format("超出地图边界x=%s,y=%s", point[1], point[0]));
        }
        return getMark(index);
    }

    /**
     * 构建当前地图变更 封装对象
     * @param version
     * @param changePoints
     * @return
     */
    private VersionData encodeVersion(long version, ArrayList<Integer[]> changePoints){
        StringBuilder body = new StringBuilder();
        StringBuilder food = new StringBuilder();
        StringBuilder remove = new StringBuilder();
        Mark mark;
        for(Integer[] p : changePoints){
            mark = getMark(p);
            if(mark == null || mark.isEmpty()){
                remove.append("," + p[1] + "," + p[0]);
            } else if(mark.snakeNodes > 0){
                body.append("," + p[1] + "," + p[0]);
            } else if (mark.foodNode > 0) {
                food.append("," + p[1] + "," + p[0]);
            }
        }
        List<String> cmd = new ArrayList<>();
        List<String> cmdDatas = new ArrayList();
        // 去掉前缀 中的逗号
        if(body.length() > 0){
            body.deleteCharAt(0);
            cmd.add("Green");
            cmdDatas.add(body.toString());
        }
        if (food.length() > 0) {
            food.deleteCharAt(0);
            cmd.add("Yellow");
            cmdDatas.add(food.toString());
        }
        if (remove.length() > 0) {
            remove.deleteCharAt(0);
            cmd.add("Black");
            cmdDatas.add(remove.toString());
        }
        VersionData vd = new VersionData(version, System.currentTimeMillis());
        vd.setCmds(cmd.toArray(new String[cmd.size()]));
        vd.setCmdDatas(cmdDatas.toArray(new String[cmdDatas.size()]));
        vd.setFull(false);
        return vd;
    }

    public void setListener(SnakeGameListener listener) {
        this.listener = listener;
    }

    public IntegralInfo getIntegralInfoByAccountId(String accountId) {
        if (!snakes.containsKey(accountId)) {
            logger.warn("找不到指定帐户");
            return null;
        }
        SnakeEntity snake = snakes.get(accountId);
        IntegralInfo info = new IntegralInfo();
        info.setLastVersion(currentVersion);
        info.setGameName(snake.getGameName());
        info.setAccountId(snake.getAccountId());
        info.setDieIntegral(snake.getDieIntegral());
        info.setKillIntegral(snake.getKillIntegral());
        return info;
    }

    public SnakeEntity getSnakeByAccountId(String accountId) {
        if (!snakes.containsKey(accountId)) {
            logger.warn("找不到指定帐户");
            return null;
        }
        return snakes.get(accountId);
    }
}
