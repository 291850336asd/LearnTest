package com.test.cinema;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        testCinema();
    }

    private static  void testCinema(){
        int typeNum = 3;
        List<CinemaInfo> allCanemas = initTypeCinemas(typeNum);

        //搜索条件
        List<SearchType> searchTypes =initSearchInfos(3, 4, 5);
        resetAndPrintAllSearchedInfos(allCanemas, true, true , false);
        searchTypesAll(allCanemas, searchTypes, 1, 1);
        resetAndPrintAllSearchedInfos(allCanemas, true, true, false);
        searchTypes =initSearchInfos(30, 4, 5);
        searchTypesAll(allCanemas, searchTypes, 1, 1);
        resetAndPrintAllSearchedInfos(allCanemas, false, true, false);
    }

    private static void searchTypesAll(List<CinemaInfo> allCanemas, List<SearchType> searchTypes, int currentTytpe, int searchCinemaIndex) {
        if(currentTytpe > searchTypes.size()){
            //搜索条件完毕退出搜索
            return;
        }
        if(!allCanemas.get(searchCinemaIndex-1).isRollEnd(currentTytpe)){
            CinemaInfo cinemaInfo = allCanemas.get(searchCinemaIndex - 1);
            cinemaInfo.setSearchType(currentTytpe);
            if(cinemaInfo.isFull()){
                //当前影厅已满搜索下一个影厅
                searchTypesAll(allCanemas, searchTypes, currentTytpe, ++searchCinemaIndex > allCanemas.size() ? 1 : searchCinemaIndex);
            }else {
                if(cinemaInfo.leftUse() >= searchTypes.get(currentTytpe -1).searchNum){
                    //有足够的厅使用
                    cinemaInfo.setCurrentUse(searchTypes.get(currentTytpe -1).searchNum);
                    //搜索下一个
                    ++currentTytpe;
                    searchTypesAll(allCanemas, searchTypes, currentTytpe, currentTytpe);
                }else {
                    int leftTypeNum =  searchTypes.get(currentTytpe- 1).searchNum - cinemaInfo.leftUse();
                    cinemaInfo.setCurrentUse(cinemaInfo.leftUse());
                    searchTypes.get(currentTytpe -1).setSearchNum(leftTypeNum);
                    searchTypesAll(allCanemas, searchTypes, currentTytpe, ++searchCinemaIndex > allCanemas.size() ? 1 : searchCinemaIndex);
                }
            }

        } else {
            //所有影厅已经搜完完毕退出搜索
            return;
        }
    }

    /**
     * 每次搜完后为下次搜索重置可用信息
     * @param allCanemas
     */
    private static void resetAndPrintAllSearchedInfos(List<CinemaInfo> allCanemas, boolean isReset, boolean isPrint, boolean isOnlyResetSearch) {
        System.out.println("------------ all infos -------------");
        allCanemas.forEach(item ->{
            if(isOnlyResetSearch){
                item.setSearchType(0);
            }else {
                if(isReset){
                    item.setTotalNun(item.getTotalNun() - item.getCurrentUse());
                    item.setCurrentUse(0);
                    item.setSearchType(0);
                }
                if(isPrint){
                    System.out.println(item);
                }
            }

        });
    }


    /**
     * 初始化搜索条件
     * @param searchBig
     * @param searchMiddle
     * @param searchSmall
     * @return
     */
    public static  List<SearchType> initSearchInfos(int searchBig, int searchMiddle, int searchSmall){
        System.out.println("search infos : " + searchBig + "  " + searchMiddle + "   " + searchSmall);
        List<SearchType> searchTypes = new ArrayList<>();
        searchTypes.add(new SearchType(searchBig, 1));
        searchTypes.add(new SearchType(searchMiddle, 2));
        searchTypes.add(new SearchType(searchSmall, 3));
        return searchTypes;
    }

    /**
     * 初始化数据
     * @param typeNums  影厅类型，默认已经排序，此为各种类型可用统计结果
     * @return
     */
    public static List<CinemaInfo> initTypeCinemas(int typeNums){
        List<CinemaInfo> allCanemas = new ArrayList<>();
        CinemaInfo cinemaInfo = null;
        for (int i = 0; i < typeNums; i++) {
            cinemaInfo = new CinemaInfo();
            cinemaInfo.setTotalNun(20);
            allCanemas.add(cinemaInfo);
        }
        return allCanemas;
    }
}
