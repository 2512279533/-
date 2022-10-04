package com.guigu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.R;
import com.guigu.GlobalExaeption.guliException;
import com.guigu.service.VodService;
import com.guigu.utils.ConstantVodUtils;
import com.guigu.utils.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin(allowCredentials = "true")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file){

        //返回上传视频id
       String videoId =vodService.uploadVideo(file);

        return R.ok().data("videoId",videoId);
    }

    //根据id删除video
    //删除阿里云中的视频
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){

        try{
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request =new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);

            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new guliException(20001,"删除视频失败");
        }

    }

    //删除多个阿里云视频的方法
    @DeleteMapping("deleteBath")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }
}
