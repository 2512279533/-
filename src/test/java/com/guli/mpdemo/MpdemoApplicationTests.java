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
        //测试乐观锁
        User user = userMapper.selectById(1L);
        user.setAge(22);

        userMapper.updateById(user);

    }

}
