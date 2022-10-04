package com.guigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.edu.entity.EduSubject;
import com.guigu.edu.entity.SubjectNestEdVo;
import com.guigu.edu.entity.SubjectVo;
import com.guigu.edu.entity.subjectData;
import com.guigu.edu.listener.subjectListener;
import com.guigu.edu.mapper.EduSubjectMapper;
import com.guigu.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-09-25
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file) {


        try {
            InputStream in= file.getInputStream();
            EasyExcel.read(in, subjectData.class,new subjectListener(this)).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<SubjectNestEdVo> nestedList() {

        //查询一级分类
        QueryWrapper<EduSubject> queryWrapper =new QueryWrapper();
        queryWrapper.eq("parent_id","0");

        List<EduSubject> oneSubjectList = baseMapper.selectList(queryWrapper);

        //查询二级分类
        QueryWrapper<EduSubject> queryWrapper2 =new QueryWrapper();
        queryWrapper2.ne("parent_id","0");

        List<EduSubject> twoSubjectList = baseMapper.selectList(queryWrapper2);

        //final最终集合
        List<SubjectNestEdVo> subjectNestEdVoList=new ArrayList<>();

        for (EduSubject eduSubject: oneSubjectList){

            //将一级分类放入最终集合
            SubjectNestEdVo subjectNestEdVo = new SubjectNestEdVo();
            BeanUtils.copyProperties(eduSubject,subjectNestEdVo);

            //继续嵌套放入二级分类
            for (EduSubject eduSubject1:twoSubjectList){

                // 如果判断是子类，放入
                if (eduSubject1.getParentId().equals(subjectNestEdVo.getId())){
                    //转成vo
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(eduSubject1,subjectVo);

                    subjectNestEdVo.getChildren().add(subjectVo);

                }
            }
            subjectNestEdVoList.add(subjectNestEdVo);
        }
        return subjectNestEdVoList;
    }
}
