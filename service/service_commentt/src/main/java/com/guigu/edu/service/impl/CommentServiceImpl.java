package com.guigu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.edu.entity.Comment;
import com.guigu.edu.mapper.CommentMapper;
import com.guigu.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public Map<String, Object> mypage(Page<Comment> page1,String courseId) {

        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
        queryWrapper.eq("course_id",courseId);

        baseMapper.selectPage(page1, queryWrapper);
        List<Comment> records = page1.getRecords();
        long current = page1.getCurrent();
        long pages = page1.getPages();
        long size = page1.getSize();
        long total = page1.getTotal();
        boolean hasNext = page1.hasNext();
        boolean hasPrevious = page1.hasPrevious();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
