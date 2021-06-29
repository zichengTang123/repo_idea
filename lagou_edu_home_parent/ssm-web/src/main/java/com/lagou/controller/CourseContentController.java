package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询成功", sectionList);
        return responseResult;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询成功", course);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if(courseSection.getId() == null){
            courseContentService.saveCourseSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "保存成功", null);
            return responseResult;
        } else {
            courseContentService.updateCourseSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新章节信息成功", null);
            return responseResult;
        }
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam Integer id, Integer status){
        courseContentService.updateSectionStatus(id, status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "更新章节状态成功", map);
        return responseResult;
    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);
        ResponseResult responseResult = new ResponseResult(true, 200, "保存成功", null);
        return responseResult;
    }

}
