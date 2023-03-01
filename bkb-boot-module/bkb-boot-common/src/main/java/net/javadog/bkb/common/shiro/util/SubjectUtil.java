package net.javadog.bkb.common.shiro.util;

import lombok.extern.slf4j.Slf4j;
import net.javadog.bkb.common.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;

/**
 * 获取Subject工具类
 *
 * @author: hdx
 * @Date: 2022-06-13 17:48
 * @version: 1.0
 **/
@Slf4j
public class SubjectUtil {

    /**
     * 获取用户信息
     */
    public static String getUserOpenId() {
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
        } catch (UnavailableSecurityManagerException e) {
            log.info("身份鉴权异常,{}", e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
        return (String) subject.getPrincipal();
    }
}
