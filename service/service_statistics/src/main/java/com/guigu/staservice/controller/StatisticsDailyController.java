package com.guigu.staservice.controller;


import com.atguigu.commonutils.R;
import com.guigu.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/staservice/sta")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService staService;

    //统计某一天注册人数，加入数据库
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day){
        staService.registerCount(day);
        return R.ok();
    }

    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,@PathVariable String end){
        Map<String,Object> map =staService.getShowData(type,begin,end);
        return R.ok().data(map);
    }
}

