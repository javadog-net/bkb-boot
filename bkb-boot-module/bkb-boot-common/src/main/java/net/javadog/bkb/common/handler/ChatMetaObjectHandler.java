package net.javadog.bkb.common.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import net.javadog.bkb.common.shiro.util.SubjectUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Description: MybatisPlus属性自动填充配置
 * @author: hdx
 * @Date: 2022-06-16 16:39
 * @version: 1.0
 **/
@Slf4j
@Component
public class ChatMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String userOpenId = SubjectUtil.getUserOpenId();
        this.setFieldValByName("createBy", userOpenId, metaObject);
        this.setFieldValByName("updateBy", userOpenId, metaObject);
        this.setFieldValByName("createTime", DateUtil.date(), metaObject);
        this.setFieldValByName("updateTime", DateUtil.date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userOpenId = SubjectUtil.getUserOpenId();
        this.setFieldValByName("updateBy", userOpenId, metaObject);
        this.setFieldValByName("updateTime", DateUtil.date(), metaObject);
    }
}
