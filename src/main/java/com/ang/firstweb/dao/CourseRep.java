package com.ang.firstweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ang.firstweb.dao.Course;
/**
 * Created by adimn on 2019/7/9.
 */

public interface CourseRep extends JpaRepository<Course,Integer> {

}
