package com.kyzy.kyzyinstallment.search.core.biz.service.search;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kyzy.kyzyinstallment.search.core.biz.service.DataLocalCacheService;
import com.kyzy.kyzyinstallment.search.core.dos.SuggestionDo;
import com.kyzy.kyzyinstallment.search.core.mapper.SuggestionMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by 90755 on 2016/11/16.
 */

public class SuggestService {

    @Autowired
    private SuggestionMapper suggestionMapper;


    @Autowired
    private DataLocalCacheService dataLocalCacheService;


    public void buildFromBrandName() {
        List<String> brandNames = dataLocalCacheService.getBrandNameList();
        if (CollectionUtils.isEmpty(brandNames)) {
            return;
        }
        List<List<String>> brandNameListWap = Lists.partition(brandNames,500);
        for(List<String> brandNameList : brandNameListWap){
            Set<String> brandNameSetTmp = Sets.newHashSet();

            brandNameList.stream().forEach( brandName -> {
                brandNameSetTmp.addAll(Arrays.asList(StringUtils.split(brandName.trim().replaceAll("[()（）]", "|"),"|")));
            });
            buildNewSuggestion(Lists.newArrayList(brandNameSetTmp));

        }
    }

    private List<SuggestionDo> buildNewSuggestion(List<String> words) {
        throw new NotImplementedException("not implement");
    }

    private Map<String,SuggestionDo> getSuggestionbyWords(List<String> words){
        List<Map<String,SuggestionDo>> suggestionMapList = suggestionMapper.selectByWords(words);
        Map<String,SuggestionDo> wordSuggestionMap = Maps.newHashMap();
        for(Map map:suggestionMapList){
            wordSuggestionMap.put((String)map.get("word"),(SuggestionDo)map.get("suggestion"));
        }
        return wordSuggestionMap;


    }


}
