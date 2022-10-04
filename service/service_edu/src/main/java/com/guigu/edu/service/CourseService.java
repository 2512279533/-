package com.guigu.edu.service;

import com.guigu.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.edu.entity.vo.CoursePublicVo;
import com.guigu.edu.query.CourseInfoVo;

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
}
