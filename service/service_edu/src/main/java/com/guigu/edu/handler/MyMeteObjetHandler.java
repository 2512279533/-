package com.guigu.edu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMeteObjetHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
//        this.setFieldValByName("version",1,metaObject);

//        this.setFieldValByName("isDeleted",0,metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("getModified",new Date(),metaObject);
    }
}
