package com.kyzy.kyzyinstallment.search.core.dos;

import org.elasticsearch.action.suggest.SuggestResponse;

import java.util.Date;

/**
 * Created by 90755 on 2016/11/16.
 */
public class SuggestionDo {

    private String word;

    private long hitCount;

    private long docCount;

    private int manualWeight;

    private Date createTime;

    private Date updateTime;

    private IndexOptionDo suggest;


    public SuggestionDo(){}

    public SuggestionDo(String word){
        this.word = word;
    }



    public IndexOptionDo getSuggest() {
        return suggest;
    }

    public void setSuggest(IndexOptionDo suggest) {
        this.suggest = suggest;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getHitCount() {
        return hitCount;
    }

    public void setHitCount(long hitCount) {
        this.hitCount = hitCount;
    }

    public long getDocCount() {
        return docCount;
    }

    public void setDocCount(long docCount) {
        this.docCount = docCount;
    }

    public int getManualWeight() {
        return manualWeight;
    }

    public void setManualWeight(int manualWeight) {
        this.manualWeight = manualWeight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
