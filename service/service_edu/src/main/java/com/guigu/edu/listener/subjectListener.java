package com.guigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.GlobalExaeption.guliException;
import com.guigu.edu.entity.EduSubject;
import com.guigu.edu.entity.subjectData;
import com.guigu.edu.service.EduSubjectService;

public class subjectListener extends AnalysisEventListener<subjectData> {

    public EduSubjectService eduSubjectService;

    public subjectListener() {
    }
    public subjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService=eduSubjectService;
    }


    //读取excel内容，一行一行读取
    @Override
    public void invoke(subjectData data, AnalysisContext analysisContext) {
        if (data == null) {
            throw new guliException(20001, "文件数据为空");
        }
        //添加一行的一级分类
        EduSubject oneSubject = existOneSubject(eduSubjectService, data.getOneSubjectName());
        if (oneSubject == null) {
            oneSubject = new EduSubject();
            oneSubject.setTitle(data.getOneSubjectName());
            oneSubject.setParentId("0");

             eduSubjectService.save(oneSubject);

        }
        //添加一行的二级分类

        EduSubject twoSubject1 = existTwoSubject(eduSubjectService, data.getTwoSubjectName(), oneSubject.getId());

        if (twoSubject1 == null) {
            EduSubject twoSubject = new EduSubject();
            twoSubject.setTitle(data.getTwoSubjectName());
            twoSubject.setParentId(oneSubject.getId());

            eduSubjectService.save(twoSubject);
        }
    }

    //查询一级分类
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");

        EduSubject oneSubject = subjectService.getOne(queryWrapper);
        return oneSubject;
    }

    //查询二级分类
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);

        EduSubject twoSubject = subjectService.getOne(queryWrapper);
        return twoSubject;

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
