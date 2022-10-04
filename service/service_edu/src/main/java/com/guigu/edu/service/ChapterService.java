package com.guigu.edu.service;

import com.guigu.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
