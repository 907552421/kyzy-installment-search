package com.kyzy.kyzyinstallment.search.api.facade;

import com.kyzy.kyzyinstallment.search.api.dto.SkuAggregationDTO;

/**
 * Created by 90755 on 2016/11/14.
 */
public interface SearchFacade {

    SkuAggregationDTO searchSkuByKeywords(String keywords,Integer sortType,String filterItems,Integer PageIndex,Integer pageSize);
}
