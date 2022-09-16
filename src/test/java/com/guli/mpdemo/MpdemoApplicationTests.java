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

        User user = userMapper.selectById(4L);

        System.out.println(user);

    }

    @Test
    void ttt(){
        User user=new User();
        user.setAge(11);
        user.setName("zzz");
        userMapper.insert(user);
    }


    @Test
    void delete(){
        userMapper.deleteById(4L);
    }
}
