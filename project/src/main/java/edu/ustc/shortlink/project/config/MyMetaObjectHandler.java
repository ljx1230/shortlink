package edu.ustc.shortlink.project.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: ljx
 * @Date: 2024/2/7 16:06
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject,"updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject,"delFlag",() -> 0, Integer.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updateTime", LocalDateTime::now, LocalDateTime.class);

    }
}
