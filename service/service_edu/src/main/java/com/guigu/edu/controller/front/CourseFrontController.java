package com.guigu.edu.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.order.CourseWebVoOrder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.edu.client.orderClient;
import com.guigu.edu.entity.Course;
import com.guigu.edu.entity.vo.ChapterVo;
import com.guigu.edu.entity.vo.CourseFrontVo;
import com.guigu.edu.entity.vo.CourseWebVo;
import com.guigu.edu.service.ChapterService;
import com.guigu.edu.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private orderClient orderClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontInfo/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<Course> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询订单是否支付
        String memberId= JwtUtils.getMemberIdByJwtToken(request);
        if (memberId==null || memberId.equals("")){
            return R.error().code(30000).message("未登录");
        }
        boolean buyCourse = orderClient.isBuyCourse(courseId,memberId);

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }

    @GetMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){

        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);

        return courseWebVoOrder;
    }
}












