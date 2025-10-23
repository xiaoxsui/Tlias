package com.xxs;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    //定义日志记录对象Logger，使用slf4j定义的日志接口
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog(){
//        System.out.println(LocalDateTime.now()+ "：开始计算...");
        log.debug("开始计算...");
        int sum = 0;
        int[] nums = { 1,3,4,2,2,5,21,5,12};
        for (int num : nums) {
            sum += num;
        }

        log.info("计算结果为：" + sum);
        log.debug("结束计算...");
//        System.out.println("计算结果为：" + sum);
//        System.out.println(LocalDateTime.now() + "：结束计算...");
    }
}
