package com.guigu.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.edu.entity.vo.CourseFrontVo;
import com.guigu.edu.entity.vo.CoursePublicVo;
import com.guigu.edu.entity.vo.CourseWebVo;
import com.guigu.edu.query.CourseInfoVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
public interface CourseService extends IService<Course> {

    String saveCourse(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublicVo publishCourseInfo(String id);

    void deleteCourseById(String courseId);

    Map<String, Object> getCourseFrontList(Page<Course> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
