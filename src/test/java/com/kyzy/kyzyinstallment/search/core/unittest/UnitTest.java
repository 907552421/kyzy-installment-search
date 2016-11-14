package com.kyzy.kyzyinstallment.search.core.unittest;

import com.kyzy.kyzyinstallment.search.core.constants.SystemConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 90755 on 2016/11/14.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class UnitTest {
//    @Test
//    public void run() {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        SystemConfig bean = ac.getBean(SystemConfig.class);
//        System.out.println(bean.getOnline());
//
//    }

//    @Resource
//    private static SystemConfig sys;


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SystemConfig bean = ac.getBean(SystemConfig.class);
        System.out.println(bean.getOnline());
    }
}
