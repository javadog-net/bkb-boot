package net.javadog.bkb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.bkb.common.enums.CommonStatusEnum;
import net.javadog.bkb.dto.request.QuesReplyRequest;
import net.javadog.bkb.dto.request.RoastReplyRequest;
import net.javadog.bkb.dto.response.QuesReplyResponse;
import net.javadog.bkb.dto.response.RoastReplyResponse;
import net.javadog.bkb.dto.response.UserResponse;
import net.javadog.bkb.entity.*;
import net.javadog.bkb.mapper.QuesReplyMapper;
import net.javadog.bkb.mapper.RoastReplyMapper;
import net.javadog.bkb.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 问题接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:55
 * @version: 1.0
 **/
@Service
public class QuesReplyServiceImpl extends ServiceImpl<QuesReplyMapper, QuesReply> implements QuesReplyService {

    @Resource
    private UserService userService;

    @Resource
    private QuesService quesService;

    @Override
    public void save(QuesReplyRequest quesReplyRequest) {
        QuesReply quesReply = new QuesReply();
        BeanUtil.copyProperties(quesReplyRequest, quesReply);
        quesReply.setStatus(CommonStatusEnum.YES.getCode());
        this.save(quesReply);
        // 增加评论量
        Ques ques = quesService.getById(quesReplyRequest.getQuesId());
        ques.setReplyNum(ques.getReplyNum()+1);
        quesService.updateById(ques);
    }

    @Override
    public IPage<QuesReplyResponse> page(QuesReplyRequest quesReplyRequest, Integer current, Integer size) {
        IPage<QuesReply> page = new Page<>(current, size);
        LambdaQueryWrapper<QuesReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(QuesReply::getStatus, CommonStatusEnum.YES.getCode())
                .eq(ObjectUtil.isNotNull(quesReplyRequest.getQuesId()), QuesReply::getQuesId, quesReplyRequest.getQuesId())
                .orderByDesc(QuesReply::getCreateTime);
        IPage<QuesReply> quesReplys = this.page(page, queryWrapper);
        IPage<QuesReplyResponse> convert = quesReplys.convert(QuesReply -> BeanUtil.copyProperties(QuesReply, QuesReplyResponse.class));
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
