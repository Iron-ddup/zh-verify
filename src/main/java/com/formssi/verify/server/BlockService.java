package com.formssi.verify.server;


import com.formssi.verify.domain.Block;

import java.math.BigInteger;

public interface BlockService {

	int getBlockHigh() throws Exception;

	Block getBlockTransLogInfo(BigInteger blockNumber) throws Exception;
}
