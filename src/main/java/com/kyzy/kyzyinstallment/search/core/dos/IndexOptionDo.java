package com.kyzy.kyzyinstallment.search.core.dos;

import java.util.List;

/**
 * Created by 90755 on 2016/11/16.
 */
public class IndexOptionDo {

    List<String> input;

    private String output;

    private int weight;

    public IndexOptionDo() {
    }

    public IndexOptionDo(List<String> input, String output, int weight) {
        this.input = input;
        this.output = output;
        this.weight = weight;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
