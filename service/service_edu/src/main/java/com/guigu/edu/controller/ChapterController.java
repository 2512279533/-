package com.guigu.edu.controller;


import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.GlobalExaeption.guliException;
import com.guigu.edu.entity.Chapter;
import com.guigu.edu.entity.Video;
import com.guigu.edu.entity.vo.ChapterVo;
import com.guigu.edu.service.ChapterService;
import com.guigu.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;

    @PostMapping("addChapter")
    public R addChapter(@RequestBody Chapter chapter){

        chapterService.save(chapter);
        return R.ok();
    }
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){

        chapterService.updateById(chapter);

        return R.ok();
    }

    //根据id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list=chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    //根据id进行查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        Chapter byId = chapterService.getById(chapterId);
        return R.ok().data("chapter",byId);
    }

    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);

        int count = videoService.count(queryWrapper);
        if (count==0){
            chapterService.removeById(chapterId);
        }else {
            throw new guliException(20001,"不能删除");
        }

        List<Video> list = videoService.list(queryWrapper);

        for (Video video:list){

        }

        return R.ok();
    }
}

