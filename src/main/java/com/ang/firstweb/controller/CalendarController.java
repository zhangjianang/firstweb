package com.ang.firstweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ang.firstweb.dao.Course;
import com.ang.firstweb.dao.CourseRep;
import com.ang.firstweb.dao.calendar.Calendar;
import com.ang.firstweb.dao.calendar.CalendarRep;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adimn on 2019/7/17.
 */
@RestController
@RequestMapping(value = "/calendar")
public class CalendarController {
    @Autowired
    CalendarRep calendarRep;

    /**
     * 查询所有列表
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Calendar> getCalendarList(){
        return calendarRep.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public int saveCalendar(@RequestParam(value = "data") String data){
        Calendar calendar = JSONObject.parseObject(data, Calendar.class);
        calendarRep.save(calendar);
        return 1;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int updateCalendar(@RequestParam(value = "data") String data){
        Calendar calendar = JSONObject.parseObject(data, Calendar.class);
        Calendar save = calendarRep.save(calendar);
        if(save!=null) {
            return 1;
        }else {
            return 0;
        }
    }
}
