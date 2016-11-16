package com.kyzy.kyzyinstallment.search.core.mapper;

import com.kyzy.kyzyinstallment.search.core.dos.SuggestionDo;

import java.util.List;
import java.util.Map;

/**
 * Created by 90755 on 2016/11/16.
 */
public interface SuggestionMapper {
    void batchInsertUpdateHitCount(List<SuggestionDo> suggestionDos);

    void batchInsertNoUpdate(List<SuggestionDo> suggestionDos);

    List<Map<String,SuggestionDo>> selectByWords(List<String> words);


}
