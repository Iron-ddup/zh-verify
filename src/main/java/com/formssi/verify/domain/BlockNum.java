package com.formssi.verify.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigInteger;
@TableName("block_number")
@Data
public class BlockNum {

    private BigInteger blockNum;
    private int blockId;

}

