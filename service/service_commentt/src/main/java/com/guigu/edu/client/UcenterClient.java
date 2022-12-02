package com.guigu.edu.client;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.order.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@FeignClient("service-ucenter")
@Component
public interface UcenterClient {
    //根据id获取用户信息
    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);


}
