//package com.formssi.verify.scheduled;
//
//import com.formssi.verify.bcos.FiscoBcosSdk;
//import com.formssi.verify.domain.Block;
//import com.formssi.verify.domain.BlockNum;
//import com.formssi.verify.domain.TransRecord;
//import com.formssi.verify.mapper.BlockMapper;
////import com.formssi.verify.server.impl.BlockDaoImpl;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.bcos.web3j.protocol.core.DefaultBlockParameter;
//import org.bcos.web3j.protocol.core.methods.response.EthBlock;
//import org.bcos.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
//import org.bcos.web3j.protocol.core.methods.response.Log;
//import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigInteger;
//import java.util.List;
//import java.util.Optional;
//
//import static java.lang.Thread.sleep;
//
//@Component
//public class SyncData {
//    private final static Logger logger = LoggerFactory.getLogger(SyncData.class);
//
//    private BlockMapper blockMapper;
//
//    public SyncData(){
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        blockMapper = new BlockDaoImpl(new SqlSessionFactoryBuilder().build(inputStream));
//    }
//
//    //线程启动
//    public void threadFun(BigInteger tag) {
//        logger.info("===========ffff=============");
//        while (true) {
//            try {
//                //区块的高度
//                BigInteger newBlockNum = FiscoBcosSdk.web3.ethBlockNumber().sendAsync().get().getBlockNumber();
//                //安装一个redis,长期查询的数据
//
////                BigInteger sqlBlockNum = blockMapper.queryBlockNum(tag);
//                if (newBlockNum.compareTo(sqlBlockNum) > 0) {
//                    for (BigInteger bigInteger = sqlBlockNum.add(BigInteger.valueOf(1)); bigInteger.compareTo(newBlockNum) <= 0;
//                         bigInteger = bigInteger.add(BigInteger.valueOf(1))) {
//
//                        //数据库的块高大于链上的跳出
//                        Boolean b = bigInteger.compareTo(newBlockNum) == 1;
//                        if (b) break;
//
//
//                        BigInteger getBlockIndex = bigInteger;
//                        if (getBlockIndex.mod(BigInteger.valueOf(10)).compareTo(tag) != 0)
//                            continue;
//                        try {
//                            SyncDataToSql(getBlockIndex, tag);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                } else {
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    //数据拉取
//    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
//    public void SyncDataToSql(BigInteger getBlockIndex, BigInteger tag) {
//
//        try {
//
//            EthBlock ethBlock = FiscoBcosSdk.web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(getBlockIndex), false).send();
//            EthBlock.Block block = ethBlock.getBlock();
//            Block retBlock = new Block();
//
//            retBlock.setBlockNumber(getBlockIndex.toString());
//            retBlock.setPreBlockHash(block.getParentHash());
//            retBlock.setCurrBlockHash(block.getHash());
//            retBlock.setBlockTime(block.getTimestampRaw());
//
//            /*将区块存入本地数据库*/
////            blockMapper.addBlock(retBlock);
//
//            TransRecord transRecord;
//
//            List<EthBlock.TransactionResult> transactionResults = block.getTransactions();
//            for (EthBlock.TransactionResult result : transactionResults) {
//                EthGetTransactionReceipt ethGetTransactionReceipt = FiscoBcosSdk.web3.ethGetTransactionReceipt((String) result.get()).send();
//                Optional<TransactionReceipt> opt = ethGetTransactionReceipt.getTransactionReceipt();
//                if (opt.isPresent()) {
//                    TransactionReceipt receipt = opt.get();
//                    List<Log> logList = receipt.getLogs();
//                    //插入数据的操作
//
//                }
//            }
//            System.out.println("retBlock:\n" + retBlock);
//            //记录该线程当前块高
//            BlockNum blockNum = new BlockNum();
//            blockNum.setBlockNumber(getBlockIndex);
//            blockNum.setTag(tag);
//            blockMapper.updateBlockNum(blockNum);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("同步链上数据到本地数据库失败!!!");
//            blockMapper.addFBlockNum(getBlockIndex);
//            e.printStackTrace();
//        }
//    }
//}
