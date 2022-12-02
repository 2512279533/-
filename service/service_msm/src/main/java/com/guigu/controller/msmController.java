package com.guigu.controller;


import com.atguigu.commonutils.R;
import com.guigu.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/edumsm/msm")
@EnableSwagger2
public class msmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
//1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        String c=msmService.dx(phone);

         return R.ok();

    }
}
