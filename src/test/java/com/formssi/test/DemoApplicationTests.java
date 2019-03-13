package com.formssi.test;

import com.formssi.verify.domain.OrderBalance;
import com.formssi.verify.mapper.OrderBalanceMapper;
import com.formssi.verify.scheduled.DownBlock;
//import com.formssi.verify.scheduled.ThreadAllNum;
import com.formssi.verify.server.AccountService;
import com.formssi.verify.server.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplicationTests.class)
public class DemoApplicationTests {
	
//	@Autowired
//	private OrderBalanceMapper orderBalanceMapper;
//
//
//
	@Test
	public void contextLoads() {
////		threadAllNum.threadUp();
////		downBlock.cesi();
////		try {
////			Thread.sleep(10000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
	}
//
 
//
//	@Test
//	public void testSomething(){
//		OrderBalance orderBalance=new OrderBalance();
//		//orderBalance.setFrom("kkk");
//		orderBalanceMapper.insert(orderBalance);
//	}

}
