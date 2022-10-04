package com.guigu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.edu.entity.Chapter;
import com.guigu.edu.entity.Video;
import com.guigu.edu.entity.vo.ChapterVo;
import com.guigu.edu.entity.vo.VideoVo;
import com.guigu.edu.mapper.ChapterMapper;
import com.guigu.edu.mapper.VideoMapper;
import com.guigu.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //根据课程id查询课程里面的所有的章节
        QueryWrapper<Chapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);

        List<Chapter> chapterList = baseMapper.selectList(queryWrapper);

        //根据课程id查询课程里面的所有的小节
        QueryWrapper<Video> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);

        List<Video> videoList = videoMapper.selectList(queryWrapper1);

        List<ChapterVo> chapterVoList=new ArrayList<>();
        //遍历查询章节  list集合进行封装
        for (Chapter chapter:chapterList){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);

            //遍历查询小节list集合，进行封装
            for (Video video:videoList){
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video,videoVo);

                if (video.getChapterId().equals(chapter.getId())) {
                    chapterVo.getChildren().add(videoVo);
                }
            }
            chapterVoList.add(chapterVo);
        }

        return chapterVoList;
    }
}
