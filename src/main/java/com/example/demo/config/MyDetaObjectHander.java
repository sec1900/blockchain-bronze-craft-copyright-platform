package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * 自动生成时间
 */
@Component
public class MyDetaObjectHander implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Object updateTime = getFieldValByName("time", metaObject);
        if(ObjectUtils.isEmpty(updateTime)) {
            this.setFieldValByName("time",new Date(),metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("time",new Date(),metaObject);

    }
}


