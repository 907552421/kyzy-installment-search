package com.kyzy.kyzyinstallment.search.core.unittest;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.kyzy.kyzyinstallment.search.api.facade.SearchFacade;
import com.kyzy.kyzyinstallment.search.core.constants.SystemConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static com.hankcs.hanlp.HanLP.convertToPinyinList;

/**
 * Created by 90755 on 2016/11/14.
 */

public class UnitTest {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SearchFacade searchFacade =  context.getBean(SearchFacade.class);
        searchFacade.suggest("jy",10).stream().forEach(suggest -> System.out.println(suggest));
    }

    @Test
    public void testHanLP(){
        List<Pinyin> pyList =  HanLP.convertToPinyinList("东方宝石");
        pyList.stream().forEach(pinyin -> {
            System.out.println(pinyin.getPinyinWithoutTone());
            System.out.println(pinyin.getFirstChar());
        });

    }
}
