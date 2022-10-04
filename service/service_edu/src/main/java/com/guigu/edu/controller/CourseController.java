package com.guigu.edu.controller;


import com.atguigu.commonutils.R;
import com.guigu.edu.entity.Course;
import com.guigu.edu.entity.vo.CoursePublicVo;
import com.guigu.edu.query.CourseInfoVo;
import com.guigu.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin(allowCredentials = "true")
public class CourseController {


    @Autowired
    private CourseService courseService;

    //课程列表  基本实现
    //TODO 完善条件查询带分页
    @GetMapping
    public R getCourseList(){
        List<Course> list = courseService.list(null);
        return R.ok().data("list",list);
    }


    @PostMapping("addCourseInfo")
    public R saveCourse(@RequestBody CourseInfoVo courseInfoVo){


        String id=courseService.saveCourse(courseInfoVo);

        return R.ok().data("courseId",id);
    }

    //根据id查询
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){

        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);

        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改
    @PutMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCourseInfo(courseInfoVo);

        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){

        CoursePublicVo coursePublicVo =courseService.publishCourseInfo(id);

        return R.ok().data("coursePublicVo",coursePublicVo);
    }

    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){

        courseService.deleteCourseById(courseId);

        return R.ok();
    }
    
}

