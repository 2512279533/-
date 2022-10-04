package com.guigu.edu.controller;


import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.guigu.GlobalExaeption.guliException;
import com.guigu.edu.client.VodClient;
import com.guigu.edu.entity.Video;
import com.guigu.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-09-26
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin(allowCredentials = "true")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video video){
        videoService.save(video);

        return R.ok();
    }

    //删除小节，同时把阿里云视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){

        //根据小节id删除视频
        Video video = videoService.getById(id);

        String videoSourceId = video.getVideoSourceId();

        if (!StringUtils.isEmpty(videoSourceId)) {
            R r = vodClient.deleteById(videoSourceId);
            if(r.getCode()==20001){
                throw new guliException(20001,"视频删除错误  熔断器。。。。");
            }
        }

        //删除小节
        videoService.removeById(id);

        return R.ok();
    }

}

