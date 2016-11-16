package com.kyzy.kyzyinstallment.search.core.condition;

import com.google.common.collect.Lists;
import com.kyzy.kyzyinstallment.search.core.constants.SystemConfig;
import com.kyzy.kyzyinstallment.search.core.enums.SearchSortType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;

import java.util.List;

/**
 * Created by 90755 on 2016/11/14.
 */
public class SkuSearchCondition {
    private final String index = SystemConfig.SEARCH_INDEX_NAME;

    private final String typeName = SystemConfig.SEARCH_TYPE_SKU;

    private Integer pageIndex = 0;

    private Integer pageSize = 0;

    private String keywords;

    private String analyzer = SystemConfig.TOKENIZER_IK_SMART;

    private Integer sortType = SearchSortType.ZONG_HE.getValue();


    public List<SortBuilder> builderSort(){

        //问题：一次查询会有包含多个排序吗？
        //问题：用反射代替if else判断？
        List<SortBuilder> builders = Lists.newArrayList();
        if(this.sortType == SearchSortType.SALES.getValue()){
            builders.add(SortBuilders.fieldSort("comtCount").order(SortOrder.DESC));
        }else if(this.sortType == SearchSortType.PRICE_LH.getValue()){
            builders.add(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if(this.sortType == SearchSortType.PRICE_LH.getValue()){
            builders.add(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else if(this.sortType == SearchSortType.NEW_PROD.getValue()){
            builders.add(SortBuilders.fieldSort("createtime").order(SortOrder.DESC));
        }
        return builders;
    }


    public List<AggregationBuilder> buildAggregation(){

        //TODO
        return null;
    }

    public QueryBuilder buildQuery(){
        //TODO
        return null;
    }





    public String getIndex() {
        return index;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = analyzer;
    }
}
