package net.javadog.bkb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.bkb.common.enums.CommonStatusEnum;
import net.javadog.bkb.common.enums.ServiceErrorEnum;
import net.javadog.bkb.common.exception.ServiceException;
import net.javadog.bkb.common.shiro.util.SubjectUtil;
import net.javadog.bkb.dto.request.RoastRequest;
import net.javadog.bkb.dto.response.RoastResponse;
import net.javadog.bkb.dto.response.UserResponse;
import net.javadog.bkb.entity.Roast;
import net.javadog.bkb.entity.User;
import net.javadog.bkb.mapper.RoastMapper;
import net.javadog.bkb.service.RoastService;
import net.javadog.bkb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 吐槽接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:55
 * @version: 1.0
 **/
@Service
public class RoastServiceImpl extends ServiceImpl<RoastMapper, Roast> implements RoastService {

    @Resource
    private UserService userService;

    @Override
    public void save(RoastRequest roastRequest) {
        // 校验公司名是否重复
        LambdaQueryWrapper<Roast> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Roast::getCompanyName, roastRequest.getCompanyName())
                .eq(Roast::getCompanyType, roastRequest.getCompanyType())
                .eq(Roast::getStatus, CommonStatusEnum.YES.getCode());
        int count = this.count(queryWrapper);
        if(count > 0){
            throw new ServiceException(ServiceErrorEnum.ROAST_EXIST_ERROR);
        }
        Roast roast = new Roast();
        BeanUtil.copyProperties(roastRequest, roast);
        this.save(roast);
    }

    @Override
    public IPage<RoastResponse> page(RoastRequest roastRequest, Integer current, Integer size) {
        IPage<Roast> page = new Page<>(current, size);
        LambdaQueryWrapper<Roast> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Roast::getStatus, CommonStatusEnum.YES.getCode())
                .like(StrUtil.isNotBlank(roastRequest.getCompanyName()), Roast::getCompanyName, roastRequest.getCompanyName())
                .eq(0==roastRequest.getMine(), Roast::getCompanyType, roastRequest.getCompanyType())
                .eq(0!=roastRequest.getMine(), Roast::getCreateBy, SubjectUtil.getUserOpenId())
                .orderByDesc(Roast::getHeats, Roast::getCreateTime);
        IPage<Roast> roasts = this.page(page, queryWrapper);
        IPage<RoastResponse> convert = roasts.convert(Roast -> BeanUtil.copyProperties(Roast, RoastResponse.class));
        return convert;
    }

    @Override
    public RoastResponse detail(String id) {
        Roast roast = this.getById(id);
        // 30秒内浏览量不增加
        Date updateTime = roast.getUpdateTime();
        // 更新浏览数
        if(DateUtil.compare(DateUtil.offsetSecond(updateTime, 30), new Date()) == -1){
            // 热度和浏览量增加
            roast.setViews(roast.getViews()+1);
            roast.setHeats(roast.getHeats()+1);
            this.updateById(roast);
        }
        RoastResponse roastResponse = new RoastResponse();
        BeanUtil.copyProperties(roast, roastResponse);
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenId, roast.getCreateBy()).eq(User::getStatus, CommonStatusEnum.YES.getCode());
        User user = userService.getOne(queryWrapper);
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        roastResponse.setUserResponse(userResponse);
        return roastResponse;
    }
}
