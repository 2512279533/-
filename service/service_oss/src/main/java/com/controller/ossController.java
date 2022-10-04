package com.controller;

import com.atguigu.commonutils.R;
import com.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/file")
@CrossOrigin
public class ossController {

    @Autowired
    private OssService ossService;


    @PostMapping ("upload")  //得到 本地需要上传 的文件
    public R uploadOssFile(MultipartFile file){

        //返回上传到oss的图片路径
        String url = ossService.uploadFileAvatar(file);


        return R.ok().data("url",url);
    }

}
