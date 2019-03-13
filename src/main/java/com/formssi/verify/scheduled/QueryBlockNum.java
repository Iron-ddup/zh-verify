package com.formssi.verify.scheduled;

import com.formssi.verify.mapper.BlockMapper;
import com.formssi.verify.mytask.SyncBlockInfoTask;

import com.formssi.verify.utils.ConstantUtil;
import org.fisco.bcos.web3j.protocol.Web3j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Component
public class QueryBlockNum {
    
    private final static Logger logger = LoggerFactory.getLogger(QueryBlockNum.class);
    
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
     Web3j web3j;
    @Autowired
    private SyncBlockInfoTask syncTask;

    @Scheduled(fixedRate = 5000)
    public void getBlockInfo(){
        ConstantUtil.DB_BLOCK_NUM=blockMapper.getLatestBlockNum();

        try {
            ConstantUtil.CHAIN_BLOCK_NUM=web3j.getBlockNumber().send().getBlockNumber();

        if (ConstantUtil.CHAIN_BLOCK_NUM.compareTo(ConstantUtil.DB_BLOCK_NUM)>0 && ConstantUtil.FLAG==0){
                for (BigInteger iterator = ConstantUtil.DB_BLOCK_NUM.add(BigInteger.ONE); ConstantUtil.CHAIN_BLOCK_NUM.compareTo(iterator)>=0; iterator=iterator.add(BigInteger.ONE)){
                    ConstantUtil.FLAG++;
                    logger.info("(◉ω◉)===>1|----------|FLAG:"+ConstantUtil.FLAG);
                    syncTask.pullBlockInfoTask(iterator);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
