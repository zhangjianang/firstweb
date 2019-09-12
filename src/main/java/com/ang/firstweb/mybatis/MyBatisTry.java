package com.ang.firstweb.mybatis;

import java.io.InputStream;

/**
 * Created by adimn on 2019/9/12.
 */
public class MyBatisTry {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        User result = session.selectOne("com.tuling.mybatis.dao.UserMapper.selectUser", 1);
        System.out.println(result.toString());
    }
}
