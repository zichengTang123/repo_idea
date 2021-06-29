package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    /*多条件查询课程*/


    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        List<Course> courseList = courseMapper.findCourseByCondition(courseVO);
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {

        try {
            Course course = new Course();
            System.out.println(courseVO);
            BeanUtils.copyProperties(course, courseVO);
            System.out.println(course);
            Date date = new Date();
            course.setUpdateTime(date);
            course.setCreateTime(date);
            courseMapper.saveCourse(course);

            int id = course.getId();

            Teacher teacher = new Teacher();
            System.out.println();
            BeanUtils.copyProperties(teacher, courseVO);
            System.out.println("teacher ==== " + teacher);
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setIsDel(0);

            courseMapper.saveTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    public CourseVO findCourseById(Integer id) {

        CourseVO courseVO = courseMapper.findCourseById(id);
        System.out.println("service+courseVO+"+courseVO);
        return courseVO;
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        try {
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVO);
            Date date = new Date();
            course.setUpdateTime(date);
            courseMapper.updateCourse(course);

            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher, courseVO);
            teacher.setUpdateTime(date);
            teacher.setCourseId(course.getId());
            courseMapper.updateTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourseStatus(Integer id, Integer status) {
        Course course = new Course();
        course.setUpdateTime(new Date());
        course.setId(id);
        course.setStatus(status);
        courseMapper.updateCourseStatus(course);
    }

}
