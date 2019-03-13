package com.formssi.verify.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.formssi.verify.domain.*;
import com.formssi.verify.dto.SelectObj;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface BlockMapper extends BaseMapper<BlockNum>{
    BigInteger getLatestBlockNum();
    
    void insertBlockNum(BlockNum blockNum);
    
    TotalData getSummary(String startDate, String endDate);
    
    TotalData getSummaryGeneration(String startDate, String endDate);
    
    List<OrderBalance> getOrderBalnaceDebitRecordList(SelectObj selectObj);

    List<OrderGeneration> getOrderGenerationDebitRecordList(SelectObj selectObj);



}
