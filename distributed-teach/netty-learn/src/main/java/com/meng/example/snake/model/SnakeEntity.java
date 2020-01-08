package com.meng.example.snake.model;

import com.meng.example.snake.gameengine.SnakeGameEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.meng.example.snake.model.SnakeDirection.*;

public class SnakeEntity {
    static final Logger logger = LoggerFactory.getLogger(SnakeEntity.class);

    private int startPoint;
    private int initBodyCount;
    private final SnakeGameEngine engine;
    private String accountId;
    private String gameName;
    private volatile SnakeDirection direction;//当前蛇移动方向
    private volatile SnakeDirection lastChangeDirection; //最后一次变更方向
    private ArrayList<Integer[]> bodys = new ArrayList<>(); //y,x
    private ArrayList<Integer[]> addNodes = new ArrayList<>();// 当前地图上添加的临时节点
    private ArrayList<Integer[]> removeNodes = new ArrayList<>();// 当前地图上移除的临时节点
    private int dieIntegral; //积分
    private int killIntegral; //击杀的角色数
    private final int bodyNodeSize = 1;//蛇单个节点大小

    public SnakeState state;

    public SnakeEntity(SnakeGameEngine engine, String accountId, int startPoint, int initBodyCount, SnakeDirection defaultDirection) {
        this.engine = engine;
        this.accountId = accountId;
        this.direction = defaultDirection;
        state = SnakeState.INACTIVE;
        this.startPoint = startPoint;
        this.initBodyCount = initBodyCount;
    }

    /**
     * 复活
     * @param startPoint
     * @param initBodyCount
     */
    public void resuregence(int startPoint, int initBodyCount){
        if(!isDie()) {
            throw new RuntimeException("未达到复活条件，角色必须是死亡状态");
        }
        state = SnakeState.INACTIVE;
        this.startPoint = startPoint;
        this.initBodyCount = initBodyCount;
    }

    public void setDirection(SnakeDirection direction){
        for(SnakeDirection oldDirection: new SnakeDirection[]{this.direction, lastChangeDirection}){
            //无效指令验证
            if(oldDirection == SnakeDirection.UP && direction == DOWN){
                return;
            }
            if (oldDirection == DOWN && direction == SnakeDirection.UP){
                return;
            }
            if (oldDirection == SnakeDirection.LEFT && direction == SnakeDirection.RIGHT){
                return;
            }
            if (oldDirection == SnakeDirection.RIGHT && direction == SnakeDirection.LEFT){
                return;
            }
            logger.debug("改变snake 移动方向 ID:{}, 当前方向：{}", accountId, direction.toString());
            this.direction = direction;
        }
    }

    /**
     * 向前移动一步
     */
    public void moveStep(){
        addLineToHead();
        removeLineToTail();
    }

    public void removeLineToTail(){
        for (int i = 0; i < bodyNodeSize && !bodys.isEmpty(); i++){
            Integer[] node = bodys.remove(bodys.size() - 1);
            if(engine.isMapRange(node)){
                removeNodes.add(node);
                engine.getMark(node).snakeNodes --;
            }
        }
    }

    /**
     * 在当前方向上添加一行节点
     * 如果当前 bodys 为null, 则基于 startPoint 进行追加
     */
    public void addLineToHead(){
        Integer[] refer = null;
        SnakeDirection direction = this.direction;
        if (bodys.isEmpty()) {
            refer = new Integer[]{startPoint, startPoint - 1};
        } else {
            //转向条件验证
            if(lastChangeDirection != direction){
                boolean turnCondition = false;
                //蛇身宽度bodyNodeSize大于1导致的以下判断
                if(lastChangeDirection == SnakeDirection.LEFT || lastChangeDirection == SnakeDirection.RIGHT){
                    //左右
                    turnCondition = bodys.get(0)[0] == bodys.get(bodyNodeSize * bodyNodeSize - bodyNodeSize)[0];
                } else if(lastChangeDirection == DOWN || lastChangeDirection == SnakeDirection.UP){
                    //上下
                    turnCondition = bodys.get(0)[1] == bodys.get(bodyNodeSize * bodyNodeSize - bodyNodeSize)[1];
                }
                //转向条件不足,强制按原方向指向
                if(!turnCondition){
                    direction = lastChangeDirection;
                }
            }
            // 计算当前行走的方向
            int turn = lastChangeDirection == null ? 0 : direction.code - lastChangeDirection.code;
            if(turn == 0){
                //直行
                refer = bodys.get(bodyNodeSize - 1);
            } else if(turn == -1 || turn == 3){
                //正在向左转
                refer = bodys.get(0);
            } else if(turn == 1 || turn == -3){
                //正在向右转
                refer = bodys.get(bodyNodeSize * bodyNodeSize - 1);
            }
        }
        Integer[] first = addNode(refer, direction);
        if(bodyNodeSize > 1){
            for(int i=0; i< bodyNodeSize - 1; i++){
                switch (direction){
                    case LEFT:
                        first = addNode(first, DOWN);
                        break;
                    case UP:
                        first = addNode(first, LEFT);
                        break;
                    case RIGHT:
                        first = addNode(first, UP);
                        break;
                    case DOWN:
                        first = addNode(first, RIGHT);
                        break;
                    default: break;
                }
            }
        }
        lastChangeDirection = direction;
    }

    private Integer[] addNode(Integer[] refer, SnakeDirection direction){
        Integer[] newNode;
        switch (direction){
            case UP:
                newNode = new Integer[]{refer[0]-1, refer[1]};
                break;
            case DOWN:
                newNode = new Integer[]{refer[0]+1, refer[1]};
                break;
            case LEFT:
                newNode = new Integer[]{refer[0], refer[1] - 1};
                break;
            case RIGHT:
                newNode = new Integer[]{refer[0], refer[1] + 1};
                break;
            default: throw new RuntimeException("direction must not error : []" + direction);
        }
        bodys.add(0, newNode);
        //是否在地图界外
        if (engine.isMapRange(newNode)) {
            //节点是否重复
            engine.getMark(newNode).snakeNodes ++;
            addNodes.add(newNode);
        }
        logger.debug("添加节点 x:{} y:{}", newNode[1], newNode[0]);
        return newNode;
    }



    /**
     * 判断snake是否死亡
     * @return
     */
    public boolean isDie(){
        return this.state == SnakeState.DIE;
    }

    /**
     * 生长一节
     */
    public void grow(){
        this.state = SnakeState.GROW;
    }


    // 增加击杀积分
    public int addKillIntegral(){
        return ++killIntegral;
    }

    public void die(){
        int bodySize = bodys.size();
        for(int i =0; i< bodySize; i++){
            removeLineToTail();
        }
        this.state = SnakeState.DIE;
        //增加死亡积分
        dieIntegral ++;
        logger.info("清除死亡角色 id:{} name:{}", accountId, gameName);
    }

    /**
     * 激活snake
     */
    public void active(){
        //添加蛇身
        for(int i=0;i< initBodyCount * bodyNodeSize; i++){
            addLineToHead();
        }
        this.state = SnakeState.ALIVE;
    }

    /**
     * 获取蛇头
     * @return
     */
    public Integer[] getHead(){
        if(bodys.isEmpty()){
            return null;
        }
        return bodys.get(0);
    }

    public void dying(){
        this.state = SnakeState.DYING;
    }

    public void flush(){
        addNodes.clear();
        removeNodes.clear();
    }

    public void offline(){
        this.state=SnakeState.OFFLINE;
    }
    public boolean isOffline() {
        return this.state == SnakeState.OFFLINE;
    }


    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void alive() {
        this.state = SnakeState.ALIVE;
    }

    public int getDieIntegral() {
        return dieIntegral;
    }

    public int getKillIntegral() {
        return killIntegral;
    }
    public String getAccountId() {
        return accountId;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public ArrayList<Integer[]> getBodys() {
        return bodys;
    }

    public ArrayList<Integer[]> getAddNodes() {
        return addNodes;
    }

    public ArrayList<Integer[]> getRemoveNodes() {
        return removeNodes;
    }

    public SnakeState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "accountId='" + accountId + '\'' +
                ", gameName='" + gameName + '\'' +
                ", state=" + state +
                ",direction=" + direction;
    }
}
