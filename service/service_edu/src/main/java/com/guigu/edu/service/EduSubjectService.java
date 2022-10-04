package com.guigu.edu.service;

import com.guigu.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.edu.entity.SubjectNestEdVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-09-25
 */
@Service
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file);

    List<SubjectNestEdVo> nestedList();
}
