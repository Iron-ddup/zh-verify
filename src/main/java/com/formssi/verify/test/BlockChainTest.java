//package com.formssi.verify.test;
//
//import com.formssi.verify.domain.OrderBalance;
//
//import com.formssi.verify.utils.ValidateUtils;
//
//import com.formssi.verify.wrapper.OrderBalanceVerify;
//import org.bcos.channel.client.Service;
//import org.bcos.web3j.abi.EventEncoder;
//import org.bcos.web3j.abi.TypeReference;
//import org.bcos.web3j.abi.datatypes.Event;
//import org.bcos.web3j.abi.datatypes.Utf8String;
//import org.bcos.web3j.abi.datatypes.generated.Uint256;
//import org.bcos.web3j.crypto.Credentials;
//import org.bcos.web3j.crypto.ECKeyPair;
//import org.bcos.web3j.crypto.Keys;
//import org.bcos.web3j.protocol.Web3j;
//import org.bcos.web3j.protocol.channel.ChannelEthereumService;
//import org.bcos.web3j.protocol.core.DefaultBlockParameterName;
//import org.bcos.web3j.protocol.core.methods.response.EthBlockNumber;
//import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.fisco.bcos.web3j.crypto.Credentials;
//import org.fisco.bcos.web3j.protocol.Web3j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import rx.Observable;
//
//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.List;
//
//public class BlockChainTest {
//
//    	private static Logger logger = LoggerFactory.getLogger(BlockChainTest.class);
//	private static ApplicationContext context=null;
//
//	public static Web3j web3;
//	public static Credentials credentials;
//
//	//初始化交易参数
//	public static BigInteger gasPrice = new BigInteger("99999999999");
//	public static BigInteger gasLimit = new BigInteger("9999999999999");
//	public static BigInteger initialWeiValue = new BigInteger("0");
////
//    public static void main(String[] args) throws Exception {
//
//		//初始化Service
//		context = new ClassPathXmlApplicationContext("classpath:fisco-bcos/applicationContext.xml");
//		Service service = context.getBean(Service.class);
//		service.run();
//
//		logger.info("(◉ω◉)===>初始化AOMP的ChannelEthereumService");
//
//		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
//		channelEthereumService.setChannelService(service);
//
//		//使用AMOP消息信道初始化web3j
//		web3 = Web3j.build(channelEthereumService);
//
//		EthBlockNumber ethBlockNumber = web3.ethBlockNumber().sendAsync().get();
//		logger.info("(◉ω◉)===>获取ethBlockNumber:{}",ethBlockNumber.getBlockNumber());
//
//		//初始化交易签名私钥
//		ECKeyPair keyPair = Keys.createEcKeyPair();
//		credentials = Credentials.create(keyPair);
//		logger.info("(◉ω◉)===>blockchain sdk init success!");
//
//		String Addr="0x919868496524eedc26dbb81915fa1547a20f8998";
//        OrderBalanceVerify balVer = OrderBalanceVerify.load(Addr, web3, credentials, gasPrice, gasLimit);
//
//        OrderBalance bal=new OrderBalance();
//        bal.setFrom("1");
//        bal.setDate("20190102");
//        bal.setAccNo("123");
//        bal.setOrdNo("105");
//        bal.setCusAmt("12000");
//        bal.setCusFee("10");
//        bal.setTotTimes("12");
//        bal.setWpTimes("10");
//        bal.setWpAmt("10000");
//        bal.setPartnerCd("ZH");
//        bal.setFqCaseNo("BAL123456");
//        bal.setTrans_hash("BALHASH1234567890");
//        TransactionReceipt receipt=balVer.reconciliation( new Uint256(Integer.valueOf(bal.getFrom())), new Uint256(Integer.parseInt(bal.getOrdNo())),new Utf8String(bal.getAccNo()),
//                new Utf8String(bal.getDate()),new Uint256(Integer.parseInt(bal.getCusAmt())),new Uint256(Integer.parseInt(bal.getCusFee())),
//                new Uint256(Integer.parseInt(bal.getTotTimes())),new Uint256(Integer.parseInt(bal.getWpTimes())),
//                new Uint256(Integer.parseInt(bal.getWpAmt())),new Utf8String(bal.getPartnerCd()),new Utf8String(bal.getFqCaseNo())).get();
//
//
//        if (ValidateUtils.isEmpty(receipt)){
//            logger.error("(T＿T)===>dealUpChainTask.bal 交易回执为空！");
//        }else {
//            logger.info("(◉ω◉)===>dealUpChainTask.bal "+receipt.getBlockNumber());
//        }
//
//        List<OrderBalanceVerify.OrderBalanceReconciliationEventEventResponse> eventList= OrderBalanceVerify.getOrderBalanceReconciliationEventEvents(receipt);
//        if (ValidateUtils.isEmpty(eventList)){
//            logger.error("(T＿T)===>dealUpChainTask.bal eventList为空");
//        }else {
//            logger.info("(◉ω◉)===>dealUpChainTask.bal "+eventList.get(0)._jsonStr);
//        }
//        logger.info("(◉ω◉)==|-------------------------|=> UpchainTask 执行完成!!  bal.getOrdNo()="+bal.getOrdNo());
//
//        Observable<OrderBalanceVerify.OrderBalanceReconciliationEventEventResponse> observable1 = balVer.orderBalanceReconciliationEventEventObservable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST);
////        observable1.subscribe((response) -> {
////            System.out.println("\n\n----------TransferSucceedEvent---------");
////            System.out.println(response._jsonStr.getValue() + "哈哈哈哈 " );
////        });
//
//        Event event = new Event("OrderBalanceReconciliationEvent",
//                Arrays.<TypeReference<?>>asList(),
//                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//
//        System.out.println( EventEncoder.encode(event));
//
//    }
//
//}
