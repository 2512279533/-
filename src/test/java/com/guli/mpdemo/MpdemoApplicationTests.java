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

    @Test
    void ttt(){
        User user=new User();
        user.setAge(11);
        user.setName("zzz");
        userMapper.insert(user);
    }


    @Test
    void delete(){
        userMapper.deleteById(2L);


    }

}
