package com.ang.firstweb.controller;

import com.ang.firstweb.dao.Course;
import com.ang.firstweb.dao.CourseRep;
import com.ang.firstweb.dao.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by adimn on 2019/7/9.
 */
@RestController
@SpringBootApplication
@RequestMapping(value = "/welcome")
public class UserController {

    @Autowired
    GirlProperties girlProperties;



    @RequestMapping(value = "/name", method = RequestMethod.GET)
    String getUserByGet(@RequestParam(value = "userName") String userName){

        return "this is new , 欢迎： " + userName +" "+ girlProperties.toString();
    }

    @GetMapping("/index")
    public String index(){
        return "firstDemo"; //当浏览器输入/index时，会返回 /static/home.html的页面
    }

}
