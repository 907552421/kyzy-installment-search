package com.kyzy.kyzyinstallment.search.core.dao.es;

import com.kyzy.kyzyinstallment.search.core.condition.SkuSearchCondition;
import com.kyzy.kyzyinstallment.search.core.constants.SystemConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.suggest.SuggestRequestBuilder;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 90755 on 2016/11/14.
 */

@Repository
public class EsDao {

    @Value("${es.cluster}")
    private String cluster;

    @Value("${es.hosts}")
    private String hosts;


    @Value("${es.port}")
    private int port;


    private  static TransportClient client = null;



    @PostConstruct
    public void initESClient(){
        Settings settings = Settings.settingsBuilder().put("cluster.name",this.cluster).build();
        try{
            EsDao.client = TransportClient.builder().settings(settings).build();
            String[] multiHosts= StringUtils.split(this.hosts,",");
            for(String host:multiHosts){
                EsDao.client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),this.port));
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    /**
     * es client 需要知道 :1.indexName,2.typeName,3.searchType,4.fromPageIndex,5.pageSize,
     *                     6.filter(在search阶段的filter只是用来构建must search查询语句的，并没有真正使用filter
     *                    7.sortList(sort方式 ),8.aggregationList(aggregation方式)
     * @param skuSearchCondition
     * @return SearchResponse
     */
    public SearchResponse searchSku(SkuSearchCondition skuSearchCondition){
        SearchRequestBuilder requestBuilder = EsDao.getClient().prepareSearch(
                skuSearchCondition.getIndex()).setTypes(skuSearchCondition.getTypeName()).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFrom(skuSearchCondition.getPageIndex()).setSize(skuSearchCondition.getPageSize());

        List<SortBuilder> sorterList = skuSearchCondition.builderSort();
        if(CollectionUtils.isEmpty(sorterList)){
            sorterList.stream().forEach(sortBuilder -> requestBuilder.addSort(sortBuilder));
        }

        List<AggregationBuilder> aggregationList = skuSearchCondition.buildAggregation();
        if(!CollectionUtils.isEmpty(aggregationList)){
            aggregationList.stream().forEach(aggregation -> requestBuilder.addAggregation(aggregation));
        }

        return requestBuilder.setQuery(skuSearchCondition.buildQuery()).execute().actionGet();


    }

    /**
     * ex client 需要知道：1.indexName,2.field字段名（现在写死在代码中叫"suggest")，3.suggestSize
     * 问题：是不是要将suggest结果字符串的得分一起返回，而不是只返回结果字符串
     * @param keywords
     * @param size
     * @return List<String>
     */
    public List<String> suggest(String keywords,int size){
        CompletionSuggestionBuilder suggestionBuilder = new CompletionSuggestionBuilder("suggest");
        suggestionBuilder.text(keywords);
        suggestionBuilder.field("suggest").size(size);
        SuggestRequestBuilder suggestRequestBuilder = EsDao.client.prepareSuggest(SystemConfig.SEARCH_INDEX_NAME).addSuggestion(suggestionBuilder);

        SuggestResponse suggestResponse = suggestRequestBuilder.execute().actionGet();

        List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> suggest = suggestResponse.getSuggest().getSuggestion("suggest").getEntries();

        //测试是否按照打分进行排序
//        for(int i =0;i<suggest.size();i++){
//            List<? extends Suggest.Suggestion.Entry.Option> options = suggest.get(i).getOptions();
//            System.out.println(options.size());
//            for (int j =0;j<options.size();j++){
//                System.out.println(options.get(j).getText().string());
//                System.out.println(options.get(j).getScore());
//            }
//        }


        List<String> suggestions = new ArrayList<>();
        suggestResponse.getSuggest().getSuggestion("suggest").getEntries().stream().forEach(sugEntry ->{
            //这给地方Collectors.toSet()会导致结果无序，Collectors.toList()会更好
            suggestions.addAll(sugEntry.getOptions().stream().map(option -> option.getText().string()).collect(Collectors.toSet()));
        });
        return suggestions;
    }

    public List<String> searchSuggest(String keywords,int size){
        SearchRequestBuilder searchRequestBuilder = EsDao.getClient().prepareSearch(SystemConfig.SEARCH_INDEX_NAME)
                .setTypes(SystemConfig.SEARCH_TYPE_SUGGEST).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFrom(0).setSize(size);
        BoolQueryBuilder matchQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("word",keywords).analyzer("ik"));

        SearchResponse searchResponse = searchRequestBuilder.setQuery(matchQueryBuilder).execute().actionGet();
        return Arrays.stream(searchResponse.getHits().getHits()).map(hit -> hit.getSource().get("word").toString()).collect(Collectors.toList());
    }






    public static TransportClient getClient() {
        return client;
    }
}
