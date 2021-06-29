package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*通过课程id查询对应的内容*/
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*通过id查找课程名*/
    public Course findCourseByCourseId(Integer id);

    /*新增章节信息*/
    public void saveCourseSection(CourseSection courseSection);

    //更新章节信息
    void updateCourseSection(CourseSection courseSection);

    /*修改章节状态*/
    public void updateSectionStatus(Integer id, Integer status);

    /*新增课时信息*/
    public void saveLesson(CourseLesson courseLesson);
}
