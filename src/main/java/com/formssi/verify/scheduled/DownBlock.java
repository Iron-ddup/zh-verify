package com.formssi.verify.scheduled;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.awt.windows.ThemeReader;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DownBlock {
    private final static Logger logger = LoggerFactory.getLogger(DownBlock.class);


    @Scheduled(fixedRate = 1000)
    public void getBlockInfo(){
        logger.info("(◉ω◉)===========拉取区块=================");
        try {
            SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println("==================start"+ Ces.FLAG+"=========================="+df.format(new Date()));
            Thread.sleep(4000);

            Ces.FLAG++;
           System.out.println("==================end"+ Ces.FLAG+"=========================="+df.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    
}
