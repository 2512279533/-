package com.guigu.edu.controller;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.order.UcenterMemberOrder;
import com.atguigu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.edu.client.UcenterClient;
import com.guigu.edu.entity.Comment;
import com.guigu.edu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-10-16
 */
@RestController
@RequestMapping("/educomment/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;
    //分页
    @PostMapping("/{page}/{limit}")
    public R getPageList(@PathVariable int page, @PathVariable int limit,String courseId){

        Page<Comment> page1=new Page<>(page,limit);

        Map<String, Object> map = commentService.mypage(page1, courseId);

        return R.ok().data(map);
    }

    @PostMapping("save")
    public R addComment(@RequestBody Comment comment,HttpServletRequest request){

        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(JwtUtils.getMemberIdByJwtToken(request));

        //增加会员id,会员头像和会员昵称,通过token
        comment.setMemberId(userInfoOrder.getId());
        comment.setNickname(userInfoOrder.getNickname());
        comment.setAvatar(userInfoOrder.getAvatar());

        commentService.save(comment);

        return R.ok();
    }
}

