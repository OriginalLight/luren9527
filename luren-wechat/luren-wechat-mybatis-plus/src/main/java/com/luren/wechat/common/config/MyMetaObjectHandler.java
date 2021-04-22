package com.luren.wechat.common.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        Object createTime = this.getFieldValByName("createTime", metaObject);
        if (createTime == null) {
            createTime = DateUtil.date(System.currentTimeMillis());
            this.setFieldValByName("createTime", createTime, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        Date updateTime = DateUtil.date(System.currentTimeMillis());
        this.setFieldValByName("updateTime", updateTime, metaObject);
    }

    @Override
    public boolean openInsertFill() {
        return true;
    }

    @Override
    public boolean openUpdateFill() {
        return true;
    }
}
