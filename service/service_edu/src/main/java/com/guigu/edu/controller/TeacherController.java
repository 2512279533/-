package com.guigu.edu.controller;


import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.edu.entity.Teacher;
import com.guigu.edu.query.TeacherQuery;
import com.guigu.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author guli
 * @since 2022-09-17
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询全部讲师")
    @GetMapping("findAll")
    public R selecetAll(){
        List<Teacher> teacherList = teacherService.list(null);

        return R.ok().data("itms",teacherList);

    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){

        boolean remove = teacherService.removeById(id);
        return R.ok();
    }

//    @ApiOperation("分页讲师列表")
//    @PostMapping("{page}/{limit}")
//    public R pageTeacherCondition(@ApiParam(name = "page",value = "当前页码",required = true)
//                          @PathVariable Long page,
//                      @ApiParam(name = "limit",value = "每页记录数",required = true)
//                      @PathVariable Long limit){
//
//        Page<Teacher> pageParam=new Page<>(page,limit);
//
//        teacherService.page(pageParam, null);
//
//        return R.ok().data("tatal", pageParam).data("rows",);
//    }

    @ApiOperation("分页讲师列表，带条件")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@RequestBody TeacherQuery teacherQuery,
                       @ApiParam(name = "page",value = "当前页码",required = true)
                       @PathVariable Long current,
                       @ApiParam(name = "limit",value = "每页记录数",required = true)
                           @PathVariable Long limit){


        Page<Teacher> pageParam=new Page<>(current,limit);

        LambdaQueryWrapper<Teacher> queryWrapper=new LambdaQueryWrapper<>();
        //name不等于空时，_____每次_____创建一个getname,里面值为query的name
        if (teacherQuery.getLevel()!=null){
            queryWrapper.eq(Teacher::getLevel,teacherQuery.getLevel());
        }

        queryWrapper.like(StringUtils.isNotEmpty(teacherQuery.getName()),Teacher::getName,teacherQuery.getName());
        queryWrapper.ge(StringUtils.isNotEmpty(teacherQuery.getBegin()),Teacher::getGmtCreate,teacherQuery.getBegin());
        queryWrapper.le(StringUtils.isNotEmpty(teacherQuery.getEnd()),Teacher::getGmtCreate,teacherQuery.getEnd());
        queryWrapper.orderByDesc(Teacher::getGmtCreate);

        teacherService.page(pageParam,queryWrapper);

        List<Teacher> records = pageParam.getRecords();

        long total = pageParam.getTotal();
        return R.ok().data("rows",records).data("total",total);

    }

    @ApiOperation("新增讲师")
    @PostMapping("addTeacher")
    public R save(@ApiParam(name = "teacher",value = "讲师对象",required = true)@RequestBody Teacher teacher, HttpServletResponse response){

        boolean save = teacherService.save(teacher);


        return R.ok();
    }

    @ApiOperation("根据id查询")
    @GetMapping("getTeacher/{id}")
    public R getById(@ApiParam(name = "id",value = "讲师id",required = true)@PathVariable String id){

        Teacher teacherServiceById = teacherService.getById(id);

        return R.ok().data("teacher",teacherServiceById);
    }

    @ApiOperation("修改")
    @PostMapping("updateTeacher")
    public R updateById(@ApiParam(name = "teacher",value = "讲师对象",required = true) @RequestBody Teacher teacher ){

        teacherService.updateById(teacher);

        return R.ok();
    }
}

