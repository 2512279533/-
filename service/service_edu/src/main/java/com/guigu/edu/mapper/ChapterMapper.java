package com.guigu.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.edu.entity.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.edu.entity.Video;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
public interface ChapterMapper extends BaseMapper<Chapter> {

    List<Chapter> selectList(QueryWrapper<Video> queryWrapper1);
}
