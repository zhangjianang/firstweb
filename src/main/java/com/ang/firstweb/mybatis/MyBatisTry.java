package com.ang.firstweb.mybatis;

import com.ang.firstweb.mybatis.dao.Ang;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

/**
 * Created by adimn on 2019/9/12.
 */

public class MyBatisTry {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
//        AngMapper mapper = session.getMapper(AngMapper.class);
//        Ang result = mapper.selectAng(1);
        Ang result = session.selectOne("com.ang.firstweb.mybatis.dao.AngMapper.selectAng", 1);
        System.out.println(result.toString());
    }

}
