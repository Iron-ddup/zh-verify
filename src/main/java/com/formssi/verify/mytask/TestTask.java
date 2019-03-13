package com.formssi.verify.mytask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Jerry on 2019/1/15.
 */
@Component
public class TestTask {
    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);
    

    @Async
    public void dealNoReturnTask() {
        logger.info("返回值为void的异步调用开始" + Thread.currentThread().getName());
        try {
            
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("返回值为void的异步调用结束" + Thread.currentThread().getName());
    }
    

}
