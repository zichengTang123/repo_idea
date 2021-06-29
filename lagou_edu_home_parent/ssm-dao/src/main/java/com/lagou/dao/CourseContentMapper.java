package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*根据id查询课程内容（章节-课时)*/
    public List<CourseSection> findSectionAndLessonByCourseId(Integer CourseId);

    /*通过id查找课程名*/
    public Course findCourseByCourseId(Integer id);

    /*新增章节信息*/
    public void saveCourseSection(CourseSection courseSection);

    /*更新章节信息*/
    void updateCourseSection(CourseSection courseSection);

    /*修改章节状态*/
    public void updateSectionStatus(CourseSection courseSection);

    /*新建课时信息*/
    public void saveLesson(CourseLesson courseLesson);
}
