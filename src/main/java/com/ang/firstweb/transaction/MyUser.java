package com.ang.firstweb.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by ang on 2019/9/12.
 */
@Component
public class MyUser {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void addAng(){
        jdbcTemplate.execute("insert into ang(name,gender) value('郜林','ladyboy')");
    }
}
