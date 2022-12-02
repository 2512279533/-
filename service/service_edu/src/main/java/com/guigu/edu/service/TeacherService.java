package com.guigu.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author guli
 * @since 2022-09-17
 */
public interface TeacherService extends IService<Teacher> {

    Map<String, Object> getTeacherFrontList(Page<Teacher> pageTeacher);
}
