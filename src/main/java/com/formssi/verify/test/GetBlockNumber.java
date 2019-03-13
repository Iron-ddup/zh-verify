package com.formssi.verify.test;

import com.formssi.verify.domain.OrderBalance;
import com.formssi.verify.utils.ValidateUtils;
import com.formssi.verify.wrapper.OrderBalanceVerify;
import com.formssi.verify.wrapper.OrderGenerationVerify;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.fisco.bcos.channel.client.Service;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * @Description: java类作用描述
 * @Author: IronStrong
 * @CreateDate: 2019/3/11 16:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/11 16:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GetBlockNumber {
    private static Logger logger = LoggerFactory.getLogger(GetBlockNumber.class);
    private static BigInteger gasPrice = new BigInteger("300000000");
    private static BigInteger gasLimit = new BigInteger("300000000");
    private static Credentials credentials;
    private static ApplicationContext context=null;



    public static void main(String[] args) throws Exception {

        context = new ClassPathXmlApplicationContext("classpath:fisco-bcos/applicationContext.xml");
        Service service = context.getBean(Service.class);
        service.run();


        ChannelEthereumService channelEthereumService = new ChannelEthereumService();

        channelEthereumService.setChannelService(service);
        channelEthereumService.setTimeout(10000);
        Web3j web3j =Web3j.build(channelEthereumService,service.getGroupId());

        credentials = GenCredential.create("b83261efa42895c38c6c2364ca878f43e77f3cddbc922bf57d0d48070f79feb6");
      if (credentials == null) {
            throw new Exception("create Credentials failed");
      }

//查询块高
        getBlockHeight(web3j);



    //部署合约
      //  deployContract(web3j);

     //合约里面赋值
     transcation(web3j);



}

public static void  getBlockHeight(Web3j web3j){
    BigInteger blockNumber = null;
    try {
        blockNumber = web3j.getBlockNumber().send().getBlockNumber();
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println(blockNumber);
    assertTrue(blockNumber.compareTo(new BigInteger("0"))>= 0);
}


public static void deployContract( Web3j web3j) throws Exception {
    //deploy contract
        OrderBalanceVerify orderBalanceVerify = OrderBalanceVerify.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
        if (orderBalanceVerify != null) {
            System.out.println("orderBalanceVerify address is: " + orderBalanceVerify.getContractAddress());


        }

    //deploy contract
    OrderGenerationVerify orderGenerationVerify = OrderGenerationVerify.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
    if (orderGenerationVerify != null) {
        System.out.println("orderBalanceVerify address is: " + orderGenerationVerify.getContractAddress());


    }

}

//交易
    public static void transcation(Web3j web3j) throws ExecutionException, InterruptedException {
        // contractGasProvider aa =new contractGasProvider();

        String Addr="0xd6c8a04b8826b0a37c6d4aa0eaa8644d8e35b79f";
        OrderBalanceVerify balVer = OrderBalanceVerify.load(Addr, web3j, credentials,new StaticGasProvider(gasPrice, gasLimit) );

        OrderBalance bal=new OrderBalance();
        bal.setFrom("1");
        bal.setDate("20190102");
        bal.setAccNo("1234");
        bal.setOrdNo("0012");
        bal.setCusAmt("12011");
        bal.setCusFee("10");
        bal.setTotTimes("12");
        bal.setWpTimes("10");
        bal.setWpAmt("10000");
        bal.setPartnerCd("ZH");
        bal.setFqCaseNo("BAL123456");
        bal.setTrans_hash("BALHASH1234567890");


//BigInteger _from, BigInteger _ordNo, String _accNo, String _date, BigInteger _cusAmt, BigInteger _cusFee, BigInteger _totTimes, BigInteger _wpTimes, BigInteger _wpAmt,
// String _partnerCd, String _fqCaseNo
        TransactionReceipt receipt=balVer.reconciliation(new BigInteger(bal.getFrom()),new BigInteger(bal.getOrdNo()),bal.getAccNo(),
                bal.getDate(),new BigInteger(bal.getCusAmt()),new BigInteger(bal.getCusFee()),
                new BigInteger(bal.getTotTimes()),new BigInteger(bal.getWpTimes()),
               new BigInteger(bal.getWpAmt()),bal.getPartnerCd(),bal.getFqCaseNo()).sendAsync().get();

        if (ValidateUtils.isEmpty(receipt)){
            logger.error("(T＿T)===>dealUpChainTask.bal 交易回执为空！");
        }else {
            logger.info("(◉ω◉)===>dealUpChainTask.bal "+receipt.getBlockNumber());
        }


        List<OrderBalanceVerify.OrderBalanceReconciliationEventEventResponse> eventList= balVer.getOrderBalanceReconciliationEventEvents(receipt);
        if (ValidateUtils.isEmpty(eventList)){
            logger.error("(T＿T)===>dealUpChainTask.bal eventList为空");
        }else {
            logger.info("(◉ω◉)===>dealUpChainTask.bal "+eventList.get(0)._jsonStr);
        }
        logger.info("(◉ω◉)==|-------------------------|=> UpchainTask 执行完成!!  bal.getOrdNo()="+bal.getOrdNo());

    }








}
