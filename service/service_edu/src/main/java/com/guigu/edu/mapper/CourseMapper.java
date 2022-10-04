package com.guigu.edu.mapper;

import com.guigu.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.edu.entity.vo.CoursePublicVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublicVo publishCourseVo(String id);
}
