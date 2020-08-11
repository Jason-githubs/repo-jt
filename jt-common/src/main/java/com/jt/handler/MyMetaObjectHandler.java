package com.jt.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //设定自动填充的属性和属性值
        this.setInsertFieldValByName("created", new Date(),metaObject);
        this.setUpdateFieldValByName("updated",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setInsertFieldValByName("updated", new Date(),metaObject);
    }
}
