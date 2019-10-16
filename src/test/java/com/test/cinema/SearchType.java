package com.test.cinema;

public class SearchType {

    int searchNum;
    int searchType;
    int isFirstSearch = 0;

    public SearchType(int searchNum, int searchType, int isFirstSearch) {
        this.searchNum = searchNum;
        this.searchType = searchType;
        this.isFirstSearch = isFirstSearch;
    }

    public SearchType(int searchNum, int searchType) {
        this.searchNum = searchNum;
        this.searchType = searchType;
    }

    public int getSearchNum() {
        return searchNum;
    }

    public void setSearchNum(int searchNum) {
        this.searchNum = searchNum;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getIsFirstSearch() {
        return isFirstSearch;
    }

    public void setIsFirstSearch(int isFirstSearch) {
        this.isFirstSearch = isFirstSearch;
    }
}
