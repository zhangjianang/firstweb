package com.ang.firstweb.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by ang on 2019/9/11.
 */
@Component
public class MyAnnoTran {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MyUser myUser;

    @Transactional(Transactional.TxType.REQUIRED)
    public void add(){
        jdbcTemplate.execute("insert into account(accountName,user,money) value ('anno','郜林',2000)");
        myUser.addAng();
        int i = 1 / 0;
    }
}
