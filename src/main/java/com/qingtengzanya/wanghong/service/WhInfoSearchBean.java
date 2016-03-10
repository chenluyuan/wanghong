package com.qingtengzanya.wanghong.service;

/**
 * @author chenluyuan (chenluyuanit@gmail.com)
 */
public class WhInfoSearchBean {

    public static final String TYPE = "TYPE";
    public static final String WX_FRIEND_NO = "WX_FRIEND_NO";
    public static final String WB_FRIEND_NO = "WB_FRIEND_NO";
    public static final String AREA = "AREA";
    public static final String SCHOOL = "SCHOOL";
    public static final String LEVEL = "LEVEL";
    public static final String REMARK = "REMARK";

    private String searchType;
    private String searchValue;

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
