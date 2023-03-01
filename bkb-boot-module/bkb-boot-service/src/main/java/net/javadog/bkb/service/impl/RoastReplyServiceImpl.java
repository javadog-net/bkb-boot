package net.javadog.bkb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.bkb.common.enums.CommonStatusEnum;
import net.javadog.bkb.dto.request.RoastReplyRequest;
import net.javadog.bkb.dto.response.RoastReplyResponse;
import net.javadog.bkb.dto.response.UserResponse;
import net.javadog.bkb.entity.Roast;
import net.javadog.bkb.entity.RoastReply;
import net.javadog.bkb.entity.User;
import net.javadog.bkb.mapper.RoastReplyMapper;
import net.javadog.bkb.service.RoastReplyService;
import net.javadog.bkb.service.RoastService;
import net.javadog.bkb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 吐槽接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:55
 * @version: 1.0
 **/
@Service
public class RoastReplyServiceImpl extends ServiceImpl<RoastReplyMapper, RoastReply> implements RoastReplyService {

    @Resource
    private UserService userService;

    @Resource
    private RoastService roastService;

    @Override
    public void save(RoastReplyRequest roastReplyRequest) {
        RoastReply roastReply = new RoastReply();
        BeanUtil.copyProperties(roastReplyRequest, roastReply);
        roastReply.setStatus(CommonStatusEnum.YES.getCode());
        this.save(roastReply);
        // 增加评论量
        Roast roast = roastService.getById(roastReplyRequest.getRoastId());
        roast.setComments(roast.getComments()+1);
        roast.setHeats(roast.getHeats()+5);
        roastService.updateById(roast);
    }

    @Override
    public IPage<RoastReplyResponse> page(RoastReplyRequest roastReplyRequest, Integer current, Integer size) {
        IPage<RoastReply> page = new Page<>(current, size);
        LambdaQueryWrapper<RoastReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoastReply::getStatus, CommonStatusEnum.YES.getCode())
                .eq(ObjectUtil.isNotNull(roastReplyRequest.getRoastId()), RoastReply::getRoastId, roastReplyRequest.getRoastId())
                .orderByDesc(RoastReply::getCreateTime);
        IPage<RoastReply> roastReplys = this.page(page, queryWrapper);
        IPage<RoastReplyResponse> convert = roastReplys.convert(RoastReply -> BeanUtil.copyProperties(RoastReply, RoastReplyResponse.class));
        convert.getRecords().forEach(item->{
            String openId = item.getCreateBy();
            LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
            userQueryWrapper.eq(User::getOpenId, openId).eq(User::getStatus, CommonStatusEnum.YES.getCode());
            User user = userService.getOne(userQueryWrapper);
            UserResponse userResponse = new UserResponse();
            BeanUtil.copyProperties(user, userResponse);
            item.setUserResponse(userResponse);
        });
        return convert;
    }
}
