package com.ang.firstweb.mybatis;

import com.ang.firstweb.mybatis.dao.Account;
import com.ang.firstweb.mybatis.dao.Ang;
import com.ang.firstweb.mybatis.dao.AngMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/9/16.
 */

public class MyBatisTryTest {

    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void generalTest() throws IOException {
        AngMapper mapper = sqlSession.getMapper(AngMapper.class);
        Ang result = mapper.selectAng(1);
//        Ang result = session.selectOne("com.ang.firstweb.mybatis.dao.AngMapper.selectAng", 1);
        System.out.println(result.toString());
    }

    @Test
    public void accoutGetTest() {
        Account result = sqlSession.selectOne("com.ang.firstweb.mybatis.dao.AccountMapper.selectAccount", 16);
        System.out.println(result);
    }

    @Test
    public void accoutAddTest() {
        Account account = new Account();
        account.setAccountName("大魔王");
        account.setUserID(2);
        account.setMoney(100);
        int insert = sqlSession.insert("com.ang.firstweb.mybatis.dao.AccountMapper.addAccount", account);
        sqlSession.commit();
        System.out.println(insert);
    }

    @Test
    public void selectAccoTest (){
        List<Object> objects = sqlSession.selectList("com.ang.firstweb.mybatis.dao.AccountMapper.selectAcco");
        System.out.println(objects);
    }
}