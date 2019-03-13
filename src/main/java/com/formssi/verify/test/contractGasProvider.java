package com.formssi.verify.test;

import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

/**
 * @Description: java类作用描述
 * @Author: IronStrong
 * @CreateDate: 2019/3/12 15:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/12 15:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


    public class contractGasProvider implements ContractGasProvider {


    private BigInteger gasPrice;
    private BigInteger gasLimit;
    @Override
        public BigInteger getGasPrice(String var1 ){

            return new BigInteger(var1);
        }

    @Override
    public BigInteger getGasPrice() {
        return null;
    }
    @Override
    public BigInteger getGasLimit(String var2 ){

        return new BigInteger(var2);
    }

    @Override
    public BigInteger getGasLimit() {
        return null;
    }
}

