package com.guli.mpdemo;

import com.guli.mpdemo.domain.User;
import com.guli.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MpdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        User user=new User();

        user.setId(1L);
        user.setAge(5);
        user.setName("xxxx");
        userMapper.updateById(user);

        System.out.println(user);

    }

}
