package com.guigu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.edu.client.VodClient;
import com.guigu.edu.entity.Chapter;
import com.guigu.edu.entity.Course;
import com.guigu.edu.entity.CourseDescription;
import com.guigu.edu.entity.Video;
import com.guigu.edu.entity.vo.CourseFrontVo;
import com.guigu.edu.entity.vo.CoursePublicVo;
import com.guigu.edu.entity.vo.CourseWebVo;
import com.guigu.edu.mapper.ChapterMapper;
import com.guigu.edu.mapper.CourseDescriptionMapper;
import com.guigu.edu.mapper.CourseMapper;
import com.guigu.edu.mapper.VideoMapper;
import com.guigu.edu.query.CourseInfoVo;
import com.guigu.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private VodClient vodClient;
    @Override
    public String saveCourse(CourseInfoVo courseInfoVo) {

        //添加课程
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);

        baseMapper.insert(course);


        //添加简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(course.getId());

        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        Course course = baseMapper.selectById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);

        //将CourseInfoVo中空描述填充

        CourseDescription courseDescription = courseDescriptionMapper.selectById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        Course course = new Course();

        BeanUtils.copyProperties(courseInfoVo,course);

        baseMapper.updateById(course);


        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());

        courseDescriptionMapper.updateById(courseDescription);


        return;
    }

    @Override
    public CoursePublicVo publishCourseInfo(String id) {

        CoursePublicVo coursePublicVo=baseMapper.publishCourseVo(id);

        return coursePublicVo;
    }
    @Override
    public void deleteCourseById(String courseId) {

        //根据id删除小节
        //先批量删除视频
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");

        List<Video> videoList = videoMapper.selectList(queryWrapper);

        List<String> stringList=new ArrayList<>();
        for (Video video:videoList){
            String id = video.getVideoSourceId();
            if (!StringUtils.isEmpty(id)) {
                stringList.add(id);
            }
        }
        //果如里面有值，再删除
        if (stringList.size()>0) {
            vodClient.deleteBatch(stringList);
        }

        videoMapper.delete(queryWrapper);

        //根据id删除章节
        QueryWrapper<Chapter> queryWrapper2=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        chapterMapper.delete(queryWrapper2);

        //根据id删除描述
        QueryWrapper<CourseDescription> queryWrapper3=new QueryWrapper<>();
        queryWrapper.eq("id",courseId);
        courseDescriptionMapper.delete(queryWrapper3);

        //根据id删除课程本身

        baseMapper.deleteById(courseId);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<Course> pageCourse, CourseFrontVo courseFrontVo) {
        //2 根据讲师id查询所讲课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接
        if(!org.springframework.util.StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!org.springframework.util.StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!org.springframework.util.StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!org.springframework.util.StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //最新
            wrapper.orderByDesc("gmt_create");
        }

        if (!org.springframework.util.StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageCourse,wrapper);

        List<Course> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();//下一页
        boolean hasPrevious = pageCourse.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
       return baseMapper.getBaseCourseInfo(courseId);
    }
}
