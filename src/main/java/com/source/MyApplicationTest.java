package com.source;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 15:53 2017/12/8</p>
 * <p>modified By: </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MyApplication.class)
public class MyApplicationTest {
    private Logger logger = Logger.getLogger(MyApplicationTest.class);
    @Test
    public void test(){
        logger.info("输出info");
        logger.debug("输出debug");
        logger.error("输出error");
    }

}
