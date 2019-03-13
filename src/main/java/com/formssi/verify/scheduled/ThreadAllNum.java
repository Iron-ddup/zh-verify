//package com.formssi.verify.scheduled;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.List;
//
////启动10个线程
//@Component
//public class ThreadAllNum {
//
//    private final static Logger logger = LoggerFactory.getLogger(ThreadAllNum.class);
//    private List<SyncThread> threads = new ArrayList<SyncThread>();
//    private int num = 2;
//
//
//    //将10个线程放在list里面
//    public void threadUp() {
//        for (int index = 0; index < num; index++) {
//            SyncThread sync=new SyncThread();
//            sync.setSync(BigInteger.valueOf(index));
//            threads.add(sync);
//            new Thread(threads.get(index)).start();
//        }
//    }
//
//
//}
