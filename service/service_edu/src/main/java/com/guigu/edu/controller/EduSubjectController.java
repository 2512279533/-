package com.guigu.edu.controller;


import com.atguigu.commonutils.R;
import com.guigu.edu.entity.EduSubject;
import com.guigu.edu.entity.SubjectNestEdVo;
import com.guigu.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-09-25
 */
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){

        eduSubjectService.saveSubject(file);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R nestedList(){

        List<SubjectNestEdVo> subjectList =eduSubjectService.nestedList();

        return R.ok().data("list",subjectList);
    }

    @GetMapping("getSubjectById/{subjectId}")
    public R getSubjectById(@PathVariable String subjectId){

        EduSubject subject = eduSubjectService.getById(subjectId);
        String title = subject.getTitle();

        return R.ok().data("title",title);
    }

}

