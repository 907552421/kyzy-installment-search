package com.kyzy.kyzyinstallment.search.core.enums;

/**
 * Created by 90755 on 2016/11/15.
 */
public enum SearchSortType {

    ZONG_HE(1,"综合排序","综合"),
    SALES(2,"销量","销量"),
    PRICE_HL(3,"价格由高到低","价格又高到低"),
    PRICE_LH(4,"价格由低到高","价格由低到高"),
    NEW_PROD(5,"新品上市","新品")
    ;


    private int value;

    private String remark;



    private String shortRemark;


    private SearchSortType(int value,String remark,String shortRemark){
        this.value = value;
        this.remark = remark;
        this.shortRemark = shortRemark;
    }





    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public String getShortRemark() {
        return shortRemark;
    }

}
