package net.javadog.bkb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.common.error.WxErrorException;
import net.javadog.bkb.dto.request.UserRequest;
import net.javadog.bkb.dto.response.UserResponse;
import net.javadog.bkb.entity.User;

/**
 * 用户接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface UserService extends IService<User> {

    UserResponse auth(String code) throws WxErrorException;

    UserResponse detail();

    void update(UserRequest userRequest);
}
