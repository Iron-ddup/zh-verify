//package com.formssi.verify.scheduled;
//
//import org.springframework.stereotype.Service;
//import java.math.BigInteger;
//
//@Service
//public class SyncThread implements Runnable {
//
//    private BigInteger tag = BigInteger.valueOf(0);
//
//    public void setSync(BigInteger tag){
//        this.tag = tag;
//    }
//
//    @Override
//    public void run() {
//        System.out.println("线程运行：" + tag);
//        SyncData syncData=new SyncData();
//        syncData.threadFun(tag);
//
//    }
//}
