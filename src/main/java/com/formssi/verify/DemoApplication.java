package com.formssi.verify;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.formssi.verify.mapper")
public class DemoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}
	@Bean
	public Web3j getWeb3j(Service service) throws Exception {
		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		service.run();
		channelEthereumService.setChannelService(service);
		channelEthereumService.setTimeout(10000);
		return Web3j.build(channelEthereumService,service.getGroupId());
	}

	@Bean
	public Credentials getCredentials() throws Exception {
		Credentials	credentials = GenCredential.create("31afe32783de23c49b24d24589fffaafdaf3ba0a8357a2ef8ee4c6739987361c");
		if (credentials == null) {
			throw new Exception("create Credentials failed");
		}
		return credentials;
	}



}
