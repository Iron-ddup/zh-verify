package com.formssi.verify.server.impl;

import com.formssi.verify.domain.Account;

import com.formssi.verify.dto.AccountDTO;
import com.formssi.verify.mapper.AccountMapper;
import com.formssi.verify.mapper.BlockMapper;
import com.formssi.verify.server.AccountService;
import com.formssi.verify.utils.BeanUtil;
import com.formssi.verify.utils.ValidateUtils;
import com.formssi.verify.vo.ResponseJson;
import org.fisco.bcos.web3j.protocol.Web3j;



import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;


@Service
public class AccountServiceImpl implements AccountService {
    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private Web3j web3j;

    @Autowired
    private AccountMapper accountMapper;
    
    @Autowired
    private BlockMapper blockMapper;

    @Override
    public Account login(AccountDTO accountDTO) {
        ResponseJson responseJson=new ResponseJson();
        String result= null;
        if (ValidateUtils.isEmpty(accountDTO.getAccountId()) || ValidateUtils.isEmpty(accountDTO.getPwd())){
            return null;
        }

        //TODO 密码加密
        Account account = BeanUtil.converter(accountDTO, Account.class);
        account=accountMapper.selectOne(account);
        if (ValidateUtils.isEmpty(account)||ValidateUtils.isEmpty(account.getPwd())){
            return null;
        }else if (!ValidateUtils.areEqual(account.getPwd(),accountDTO.getPwd())){
            return null;
        }
        logger.debug("(☆∀☆)===> return result:"+result);
        return account;
    }
    
    public void getBlockTransInfo(BigInteger blockNumber)  {
//
//        EthBlock ethBlock = null;
//        try {
//            ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false).send();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        EthBlock.Block block = ethBlock.getBlock();
//
//        List<EthBlock.TransactionResult> transactionResults = block.getTransactions();
//
//
//        for (EthBlock.TransactionResult result : transactionResults) {
//            EthGetTransactionReceipt ethGetTransactionReceipt = null;
//            try {
//                ethGetTransactionReceipt = web3j.ethGetTransactionReceipt((String) result.get()).send();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Optional<TransactionReceipt> opt = ethGetTransactionReceipt.getTransactionReceipt();
//            if (opt.isPresent()) {
//                TransactionReceipt receipt = opt.get();
//
//                List<Log> logList=receipt.getLogs();
//                for (Log log:logList){
//                    logger.info("(◉ω◉)===>topics:"+log.getTopics());
//                    logger.info("(◉ω◉)===>logs:"+log.getData());
//                }
//
//            }
//        }
//
   }


}
