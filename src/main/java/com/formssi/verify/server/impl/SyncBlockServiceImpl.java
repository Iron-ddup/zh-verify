package com.formssi.verify.server.impl;

import com.formssi.verify.mapper.BlockMapper;
import com.formssi.verify.mytask.SyncBlockInfoTask;
import com.formssi.verify.mytask.TestTask;
import com.formssi.verify.server.SyncBlockService;
import com.formssi.verify.utils.ConstantUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jerry on 2019/1/15.
 */
@Service
public class SyncBlockServiceImpl implements SyncBlockService {
    private final static Logger logger = LoggerFactory.getLogger(SyncBlockServiceImpl.class);
    
    @Autowired
    private SyncBlockInfoTask syncTask;
    
    @PostConstruct
    @Override
    public void SyncBlock() {
//        try{
//            BigInteger dbBlockNum;
//            BigInteger blockBlockNum;
//            for (int i=0;i<5;i++){
//                dbBlockNum=blockMapper.getLatestBlockNum();
//                blockBlockNum=web3j.ethBlockNumber().sendAsync().get().getBlockNumber();
//                logger.info("(◉ω◉)===>dbBlockNum:"+dbBlockNum+"|blockBlockNum:"+blockBlockNum);
//                if (blockBlockNum.compareTo(dbBlockNum)>0 && FLAG==0){
//                    logger.info("(◉ω◉)===>1|-------------|"+(blockBlockNum.compareTo(dbBlockNum)>0 && FLAG==0));
//                    for (BigInteger iterator=dbBlockNum.add(new BigInteger("1"));blockBlockNum.compareTo(iterator)>=0;iterator=iterator.add(new BigInteger("1"))){
//                        FLAG++;
//                        logger.info("(◉ω◉)===>2|----------|FLAG:"+FLAG);
//                        syncTask.pullBlockInfoTask(iterator);
//                    }
//                }
//            }
//        }catch (InterruptedException|ExecutionException e) {
//            e.printStackTrace();
//        }
//        int i=0;
//        while (true){
//            i++;
//            logger.info("(◉ω◉)===>dbBlockNum:"+ConstantUtil.DB_BLOCK_NUM+"|CHAIN_BLOCK_NUM:"+ConstantUtil.CHAIN_BLOCK_NUM);
//            if (ConstantUtil.CHAIN_BLOCK_NUM.compareTo(ConstantUtil.DB_BLOCK_NUM)>0 && ConstantUtil.FLAG==0){
//                logger.info("(◉ω◉)===>1|-------------|"+(ConstantUtil.CHAIN_BLOCK_NUM.compareTo(ConstantUtil.DB_BLOCK_NUM)>0 && ConstantUtil.FLAG==0));
//                for (BigInteger iterator=ConstantUtil.DB_BLOCK_NUM.add(BigInteger.ONE);ConstantUtil.CHAIN_BLOCK_NUM.compareTo(iterator)>=0;iterator=iterator.add(BigInteger.ONE)){
//                    ConstantUtil.FLAG++;
//                    logger.info("(◉ω◉)===>2|----------|FLAG:"+ConstantUtil.FLAG);
//                    syncTask.pullBlockInfoTask(iterator);
//                }
//            }
//            if (i%10==0){
//                try {
//                    Thread.sleep(20000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
        
    }
}
