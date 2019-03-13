//package com.formssi.verify.server.impl;
//
//import com.formssi.verify.domain.Block;
//import com.formssi.verify.domain.BlockNum;
//import com.formssi.verify.domain.TransRecord;
//import com.formssi.verify.mapper.BlockMapper;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import java.math.BigInteger;
//
//public class BlockDaoImpl implements BlockMapper {
//
//    private SqlSessionFactory factory;
//    public BlockDaoImpl(SqlSessionFactory factory) { this.factory=factory;}
//
//    @Override
//    public void addBlock(Block block) {
//        SqlSession session = factory.openSession();
//        session.insert("BlockMapper.addBlock",block);
//        session.commit();
//        session.close();
//    }
//
//    @Override
//    public BigInteger queryBlockNum(BigInteger tag) {
//        SqlSession session = factory.openSession();
//        BigInteger ret = session.selectOne("BlockMapper.queryBlockNum",tag);
//        session.close();
//        return ret;
//    }
//
//    @Override
//    public void updateBlockNum(BlockNum blockNum) {
//        SqlSession session = factory.openSession();
//        session.update("BlockMapper.updateBlockNum",blockNum);
//        session.commit();
//        session.close();
//    }
//
//    @Override
//    public void updateTransRecord(TransRecord transRecord) {
//        SqlSession session = factory.openSession();
//        session.insert("BlockMapper.updateTransRecord",transRecord);
//        session.commit();
//        session.close();
//    }
//
//    @Override
//    public void addFBlockNum(BigInteger blockNum) {
//        SqlSession session = factory.openSession();
//        session.insert("BlockMapper.updateTransRecord",blockNum);
//        session.commit();
//        session.close();
//    }
//}
