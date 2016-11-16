package com.kyzy.kyzyinstallment.search.core.biz.service.search;

import com.kyzy.kyzyinstallment.search.api.dto.SkuAggregationDTO;
import com.kyzy.kyzyinstallment.search.core.condition.SkuSearchCondition;
import com.kyzy.kyzyinstallment.search.core.dao.es.EsDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 90755 on 2016/11/14.
 */

@Service
public class SearchService {

    @Autowired
    private EsDao esDao;


    /**
     * es client 执行搜索时需要知道 :1.indexName,2.typeName,3.searchType,4.fromPageIndex,5.pageSize,
     *                     6.filter(在search阶段的filter只是用来构建must search查询语句的，并没有真正使用filter
     *                    7.sortList(sort方式 ),8.aggregationList(aggregation方式)
     * @param skuSearchCondition
     * @return SkuAggregationDTO
     */
    public SkuAggregationDTO searchSkuByKeywords(SkuSearchCondition skuSearchCondition){
        //
        return null;
    }


    /**
     * es client 执行suggest时需要知道：1.indexName,2.Suggest字段名(目前写死在程序中叫做"suggest"),3.keywords,4 .suggestSize
     * @param keywords
     * @param suggestSize
     * @return List<String>
     */
    public List<String> suggest (String keywords,int suggestSize){
        List<String> suggestList = this.esDao.suggest(keywords,suggestSize);
        if( CollectionUtils.isEmpty(suggestList)|| suggestList.size()<suggestSize){
            suggestList.addAll(this.esDao.searchSuggest(keywords,suggestSize - suggestList.size()));
        }
        return suggestList;
    }

    public List<String> subSuggest(String keywords){
        throw new NotImplementedException("not implement");
    }
}
