package net.javadog.bkb.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import net.javadog.bkb.common.enums.CommonStatusEnum;
import net.javadog.bkb.common.shiro.util.JwtUtil;
import net.javadog.bkb.common.shiro.util.SubjectUtil;
import net.javadog.bkb.common.util.NickNameUtil;
import net.javadog.bkb.dto.request.UserRequest;
import net.javadog.bkb.dto.response.UserResponse;
import net.javadog.bkb.entity.User;
import net.javadog.bkb.mapper.UserMapper;
import net.javadog.bkb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:55
 * @version: 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private WxMaService wxMaService;

    @Override
    public UserResponse auth(String code) throws WxErrorException {
        WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        String openId = sessionInfo.getOpenid();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenId, openId);
        User user = this.getOne(queryWrapper);
        if(ObjectUtil.isNull(user)){
            // 创建用户并返回token
            String avatar = "http://api.multiavatar.com/" + (int) (Math.random() * 100) + ".png";
            user = new User();
            user.setAvatar(avatar);
            user.setNickname(NickNameUtil.getName());
            user.setOpenId(openId);
            user.setStatus(CommonStatusEnum.YES.getCode());
            this.save(user);
        }
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        userResponse.setAccessToken(JwtUtil.createToken(openId));
        return userResponse;
    }

    @Override
    public UserResponse detail() {
        String userOpenId = SubjectUtil.getUserOpenId();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenId, userOpenId).eq(User::getStatus, CommonStatusEnum.YES.getCode());
        User user = this.getOne(queryWrapper);
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        return userResponse;
    }

    @Override
    public void update(UserRequest userRequest) {
        User user = new User();
        BeanUtil.copyProperties(userRequest, user);
        this.updateById(user);
    }
}
