package com.formssi.verify.autoconfigure;

import com.formssi.verify.utils.PropertiesUtil;
import com.formssi.verify.wrapper.OrderBalanceVerify;
import com.formssi.verify.wrapper.OrderGenerationVerify;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @Description: java类作用描述
 * @Author: IronStrong
 * @CreateDate: 2019/3/12 16:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/12 16:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Configuration
public class ObjectToBean {
 //之后直接从配置文件里面读取
//    contract.address.genverifymapping=0xfcf4a1f566d1e0aa06436098c09d35d9762bf240
//    contract.address.balverifymapping=0xd6c8a04b8826b0a37c6d4aa0eaa8644d8e35b79f



    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;

    private static BigInteger gasPrice = new BigInteger("300000000");
    private static BigInteger gasLimit = new BigInteger("300000000");

    @Bean
    public  OrderBalanceVerify getOrderBalanceVerify(){
        String verifyBalMappingContAddr="";
        try {
            PropertiesUtil.readFile("application.properties");
            verifyBalMappingContAddr=PropertiesUtil.readValue("contract.address.balverifymapping");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String Addr="0xd6c8a04b8826b0a37c6d4aa0eaa8644d8e35b79f";
        OrderBalanceVerify balVer = OrderBalanceVerify.load(verifyBalMappingContAddr, web3j, credentials,new StaticGasProvider(gasPrice, gasLimit) );
        return balVer;
    }

    @Bean
    public OrderGenerationVerify getOrderGenerationVerify(){
        String verifyGenMappingContAddr="";
        try {
            PropertiesUtil.readFile("application.properties");
            verifyGenMappingContAddr=PropertiesUtil.readValue("contract.address.genverifymapping");
        } catch (IOException e) {
            e.printStackTrace();
        }

      //  String Addr="0xfcf4a1f566d1e0aa06436098c09d35d9762bf240";
        OrderGenerationVerify genVer = OrderGenerationVerify.load(verifyGenMappingContAddr, web3j, credentials,new StaticGasProvider(gasPrice, gasLimit) );
        return genVer;
    }
}
