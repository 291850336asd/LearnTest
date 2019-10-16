package com.test.cinema;

public class CinemaInfo {

    private int currentUse;
    private int totalNun;
    private int searchType = 0;


    public int getCurrentUse() {
        return currentUse;
    }

    public void setCurrentUse(int currentUse) {
        this.currentUse =+ currentUse;
    }

    public int getTotalNun() {
        return totalNun;
    }



    public void setTotalNun(int totalNun) {
        this.totalNun = totalNun;
    }

    public int leftUse(){
        return this.totalNun - this.currentUse;
    }

    public boolean isFull() {
        return totalNun - currentUse == 0;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public boolean isRollEnd(int searchType){
        return this.searchType == searchType;
    }

    @Override
    public String toString() {
        return "com.test.cinema.CinemaInfo{" +
                "currentUse=  " + currentUse +
                ", totalNun=  " + totalNun +
                ", searchType=  " + searchType +
                '}';
    }
}
