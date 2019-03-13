package com.formssi.verify.mytask;

import com.formssi.verify.domain.BlockNum;
import com.formssi.verify.domain.OrderBalance;
import com.formssi.verify.domain.OrderGeneration;
import com.formssi.verify.mapper.BlockMapper;
import com.formssi.verify.mapper.OrderBalanceMapper;
import com.formssi.verify.mapper.OrderGenerationMapper;
import com.formssi.verify.utils.ConstantUtil;
import org.apache.commons.codec.binary.Hex;
import org.fisco.bcos.web3j.*;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.methods.response.BcosBlock;
import org.fisco.bcos.web3j.protocol.core.methods.response.BcosTransactionReceipt;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by Jerry on 2019/1/14.
 */
@Component
public class SyncBlockInfoTask {
    //OrderGeneratReconciliationEvent(string):297e8b4faeed86af67abe4f483e104d33d96381ee09b1b6ca0b8ab874a4f70b2
    public static String GEN_EVENT_TOPIC="0x"+"297e8b4faeed86af67abe4f483e104d33d96381ee09b1b6ca0b8ab874a4f70b2";
    //OrderBalanceReconciliationEvent(string):dc20f36e0df76c3aef9484968da04d09fb91a21595f9f6049cd91ad5a1c41572
    public static String BAL_EVENT_TOPIC="0x"+"dc20f36e0df76c3aef9484968da04d09fb91a21595f9f6049cd91ad5a1c41572";
    
    private final static Logger logger = LoggerFactory.getLogger(SyncBlockInfoTask.class);
    @Autowired
    private Web3j web3j;
    
    @Autowired
    private OrderGenerationMapper genMapper;
    @Autowired
    private OrderBalanceMapper balMapper;
    @Autowired
    private BlockMapper blockMapper;
    
    @Transactional
    @Async
    public void pullBlockInfoTask(BigInteger blockNum){
        logger.info("(◉ω◉)===>3|----pullBlockInfoTask-------------|:"+ConstantUtil.FLAG+"|blockNum:"+blockNum);

        BcosBlock ethBlock = null;
        try {
            ethBlock = web3j.getBlockByNumber(DefaultBlockParameter.valueOf(blockNum), false).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BcosBlock.Block block = ethBlock.getBlock();

        List<BcosBlock.TransactionResult> transactionResults = block.getTransactions();
        for (BcosBlock.TransactionResult result : transactionResults) {
            BcosTransactionReceipt ethGetTransactionReceipt = null;
            try {
                ethGetTransactionReceipt = web3j.getTransactionReceipt((String) result.get()).send();
                Optional<TransactionReceipt> opt = ethGetTransactionReceipt.getTransactionReceipt();
                if (opt.isPresent()) {
                    TransactionReceipt receipt = opt.get();
                    List<Log> logList=receipt.getLogs();
                    for (Log log:logList){
                        logger.info("(☆∀☆)=#=|--"+"|blockNum:"+blockNum+"----------|=>log.getTopics()="+log.getTopics());
                        logger.info("(☆∀☆)=#=|--"+"|blockNum:"+blockNum+"----------|=>log.getData()="+log.getData());

                        if (GEN_EVENT_TOPIC.equals(log.getTopics().get(0))){ //GEN
                            logger.info("(☆∀☆)===|this is my debug Flag|=>Flag==>GEN");
                            //GEN [ == 5b ] == 5d
                            //这里截取到的是一个数组格式的16进制字符串。大概样式是[{xxx:xxx,...xxx:xxx},{xxx:xxx,...xxx:xxx},{xxx:xxx,...xxx:xxx}]
//                            String arrStr=log.getData().substring(log.getData().indexOf("5b")+2,log.getData().indexOf("5d"));
                            //返回的是对象的字符串list，每个记录大概是{xxx:xxx,...xxx:xxx}


                            List<String> strObjList=spilString(log.getData());

                            String tmpState="0";
                            if (strObjList.size()==2){
                                tmpState="1";
                            }
                            //循环list,将每个记录转换成字符串，然后解析json成对象
                            for (String objStr:strObjList){
                                logger.info("(☆∀☆)=#=|GEN--"+"|blockNum:"+blockNum+"-----------|objStr:"+objStr+"|hex:"+hexStringToString(objStr));
                                OrderGeneration gen=OrderGeneration.parse(hexStringToString(objStr));
                                gen.setReconciliationState(tmpState);
                                logger.info("(◉ω◉)=|---"+"|blockNum:"+blockNum+"-----------------|==>gen.JSON:"+gen.toJSON());
                                genMapper.insert(gen);
                            }

                        }else if (BAL_EVENT_TOPIC.equals(log.getTopics().get(0))){ //BAL
                            logger.info("(☆∀☆)===|this is my debug Flag|=>Flag==>BAL");
                            //返回的是对象的字符串list，每个记录大概是{xxx:xxx,...xxx:xxx}
                            List<String> strObjList=spilString(log.getData());

                            String tmpState="0";
                            if (strObjList.size()==2){
                                tmpState="1";
                            }
                            //循环list,将每个记录转换成字符串，然后解析json成对象
                            for (String objStr:strObjList){
                                logger.info("(☆∀☆)=#=|BAL--"+"|blockNum:"+blockNum+"--------|objStr:"+objStr+"|hex:"+hexStringToString(objStr));
                                OrderBalance bal=OrderBalance.parse(hexStringToString(objStr));
                                bal.setReconciliationState(tmpState);
                                logger.info("(◉ω◉)=|----------"+"|blockNum:"+blockNum+"-----------|==>bal.JSON:"+bal.toJSON());
                                balMapper.insert(bal);
                            }
                        }else {
                            continue;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        //写入块数据
        BlockNum t=new BlockNum();
        t.setBlockNum(blockNum);
        t.setBlockId(1); //该字段暂时没啥用，都写1。本意想写入单个块中的有效交易个数的
        logger.info("(☆∀☆)===>BlockNum.toString:"+t.toString());
        blockMapper.insert(t);

        logger.info("(◉ω◉)===>SyncBlockServiceImpl.FLAG:"+ConstantUtil.FLAG);
        ConstantUtil.FLAG--;

    }
    
    //16进制转换String
    public static String hexStringToString(String hex) throws Exception{
        return new String(Hex.decodeHex(hex.toCharArray()), "UTF-8");
    }
    //切分字符串
    private List<String> spilString(String msgStr){
        //截取有用字符串
        msgStr=msgStr.substring(msgStr.indexOf("5b")+2,msgStr.indexOf("5d"));
        
        List<String> tmp=new ArrayList<>();
        while (true){
            tmp.add(msgStr.substring(msgStr.indexOf("7b"),msgStr.indexOf("7d")+2));
            if (msgStr.indexOf("7d")+2>=msgStr.length()){
                break;
            }
            msgStr=msgStr.substring(msgStr.indexOf("7d")+2,msgStr.length());
        }
        return tmp;
    }
    
}
