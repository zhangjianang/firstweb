package com.ang.firstweb.controller;

import com.ang.firstweb.dao.Course;
import com.ang.firstweb.dao.CourseRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adimn on 2019/7/9.
 *  注意mysql connector的 版本，我的docker中 是 8.x
 */

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    CourseRep courseRep;

    /**
     * 查询所有列表
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Course> getGirlList(){
        return courseRep.findAll();
    }

    /**
     * 添加一个
     * @param courName
     * @param isTarget
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public Course addGirl(@RequestParam("courName") String courName,
                          @RequestParam("isTarget") Integer isTarget){
        Course course = new Course();
        course.setCo_name(courName);
        course.setIs_target(isTarget);

        return courseRep.save(course);
    }
}
