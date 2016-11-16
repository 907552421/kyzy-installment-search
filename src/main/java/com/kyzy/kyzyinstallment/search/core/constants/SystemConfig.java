package com.kyzy.kyzyinstallment.search.core.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 90755 on 2016/11/14.
 */

@Component
public class SystemConfig {

    public static final String BI_INDEX_NAME = "mall-trace";

    public static final String BI_TYPE_TRACE = "trace";

    public static final String SEARCH_INDEX_NAME = "mall";

    public static final String SEARCH_TYPE_SKU = "sku";

    public static final String SEARCH_TYPE_SUGGEST = "suggestion";

    public static final String TOKENIZER_IK_SMART = "ik_smart";

    public static final String TOKENIZER_IK = "ik";

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Value("${online}")
    public boolean online;

}
