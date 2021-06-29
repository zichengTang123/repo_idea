package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseService {

    /*多条件查询课程*/
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*新增课程信息和讲师信息*/
    public void saveCourseOrTeacher(CourseVO courseVO);

    /*课程信息回显*/
    public CourseVO findCourseById(Integer id);

    /*更新课程信息*/
    public void updateCourseOrTeacher(CourseVO courseVO);

    /*更新课程状态*/
    public void updateCourseStatus(Integer id, Integer status);



}
