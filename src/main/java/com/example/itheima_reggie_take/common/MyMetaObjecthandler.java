package com.example.itheima_reggie_take.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 */
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {
    /**
     * 传入操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段填充insert");
        log.info(metaObject.toString());

        Long currentId = BaseContext.getCurrentId();
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", currentId);
        metaObject.setValue("updateUser", currentId);
    }

    /**
     * 更新操作自动填充
     *
     * @param metaObject
     */

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段填充update");
        log.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id:{}", id);
        Long currentId = BaseContext.getCurrentId();

        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", currentId);


    }
}
