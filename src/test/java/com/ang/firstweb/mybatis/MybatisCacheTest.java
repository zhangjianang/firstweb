package com.ang.firstweb.mybatis;


import com.ang.firstweb.mybatis.dao.Ang;
import com.ang.firstweb.mybatis.dao.AngMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by adimn on 2019/9/17.
 */
public class MybatisCacheTest {

    private SqlSessionFactory sessionFactory;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        String source="mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(source);
         sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        设置true，允许自动提交
         sqlSession = sessionFactory.openSession(true);
    }

    @Test
    public void firstCacheTest(){
//        AngMapper mapper = sqlSession.getMapper(AngMapper.class);
//        System.out.println(mapper.selectAng(1));

        Ang ang = sqlSession.selectOne("AngMapper.selectAng", 1);
        System.out.println(ang);
        Ang ang2 = sqlSession.selectOne("AngMapper.selectAng", 1);
        System.out.println(ang==ang2);
        Ang ang3 = sessionFactory.openSession().selectOne("AngMapper.selectAng", 1);
        System.out.println(ang2 == ang3);
    }

    @Test
    public void clearCacheTest(){
        Ang ang = sqlSession.selectOne("AngMapper.selectAng", 1);
        sqlSession.clearCache(); // 清空了一缓存
        Ang ang2 = sqlSession.selectOne("AngMapper.selectAng", 1);
        System.out.println(ang==ang2);
    }

    @Test
    public void updateClearCacheTest(){

        Ang ang = sqlSession.selectOne("AngMapper.selectAng", 1);
//        sqlSession.clearCache(); // 清空了一缓存
        Ang param = new Ang();
        param.setName("缓存增加4");
        param.setGender(1);
        System.out.println(sqlSession.insert("AngMapper.addAng", param));
//        sqlSession.commit();
        Ang ang2 = sqlSession.selectOne("AngMapper.selectAng", 1);
        System.out.println(ang==ang2);
    }
}
