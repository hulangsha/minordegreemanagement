package com.sicau.minordegreemanagement.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充值
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        if (null == createTime){
            setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        }
        Object creator = getFieldValByName("creator", metaObject);
        if(null == creator){
            setFieldValByName("creator","admin",metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (null == updateTime){
            setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        }
        Object operator = getFieldValByName("operator", metaObject);
        if(null == operator){
            setFieldValByName("operator","admin",metaObject);
        }
    }


}
