package com.ang.firstweb.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ang on 2019/9/13.
 */
public interface AngMapper {

    @Select("select * from ang where id = #{id}")
    Ang selectAng(Integer id);

    List<Ang> selectAll();
}
