package com.kyzy.kyzyinstallment.search.core.facade;

import com.kyzy.kyzyinstallment.search.api.dto.SkuAggregationDTO;
import com.kyzy.kyzyinstallment.search.api.facade.SearchFacade;
import com.kyzy.kyzyinstallment.search.core.biz.service.search.SearchService;
import com.kyzy.kyzyinstallment.search.core.condition.SkuSearchCondition;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 90755 on 2016/11/14.
 */
@Service("searchFacade")
public class SearchFacadeImpl implements SearchFacade {

    @Autowired
    private SearchService searchService;

    @Override
    public SkuAggregationDTO searchSkuByKeywords(String keywords, Integer sortType, String filterItems, Integer PageIndex, Integer pageSize) {

        return  null;
    }

    /**
     * @param keywords
     * @param suggestSize
     * @return List<String>
     */
    @Override
    public List<String> suggest(String keywords, int suggestSize) {
        return this.searchService.suggest(keywords,suggestSize);
    }


    public List<String> subSuggest(String keywords){
        throw new NotImplementedException();
    }


}
