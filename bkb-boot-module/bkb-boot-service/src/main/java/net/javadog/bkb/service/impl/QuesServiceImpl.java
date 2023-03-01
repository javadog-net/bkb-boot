package net.javadog.bkb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.bkb.common.enums.CommonStatusEnum;
import net.javadog.bkb.common.enums.ServiceErrorEnum;
import net.javadog.bkb.common.exception.ServiceException;
import net.javadog.bkb.common.shiro.util.SubjectUtil;
import net.javadog.bkb.dto.request.QuesRequest;
import net.javadog.bkb.dto.response.QuesResponse;
import net.javadog.bkb.dto.response.RoastResponse;
import net.javadog.bkb.dto.response.UserResponse;
import net.javadog.bkb.entity.Ques;
import net.javadog.bkb.entity.Roast;
import net.javadog.bkb.entity.User;
import net.javadog.bkb.mapper.QuesMapper;
import net.javadog.bkb.service.QuesService;
import net.javadog.bkb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 提问接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:55
 * @version: 1.0
 **/
@Service
public class QuesServiceImpl extends ServiceImpl<QuesMapper, Ques> implements QuesService {

    @Resource
    private UserService userService;

    @Override
    public void save(QuesRequest quesRequest) {
        // 校验公司名是否重复
        LambdaQueryWrapper<Ques> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Ques::getQuesTitle, quesRequest.getQuesTitle()).eq(Ques::getStatus, CommonStatusEnum.YES.getCode());
        int count = this.count(queryWrapper);
        if (count > 0) {
            throw new ServiceException(ServiceErrorEnum.QUES_EXIST_ERROR);
        }
        Ques ques = new Ques();
        BeanUtil.copyProperties(quesRequest, ques);
        this.save(ques);
    }

    @Override
    public IPage<QuesResponse> page(QuesRequest quesRequest, Integer current, Integer size) {
        IPage<Ques> page = new Page<>(current, size);
        LambdaQueryWrapper<Ques> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ques::getStatus, CommonStatusEnum.YES.getCode())
                .like(StrUtil.isNotBlank(quesRequest.getQuesTitle()), Ques::getQuesTitle, quesRequest.getQuesTitle())
                .eq(0!=quesRequest.getMine(), Ques::getCreateBy, SubjectUtil.getUserOpenId())
                .orderByDesc(Ques::getReplyNum, Ques::getCreateTime);
        IPage<Ques> ques = this.page(page, queryWrapper);
        IPage<QuesResponse> convert = ques.convert(Ques -> BeanUtil.copyProperties(Ques, QuesResponse.class));
        return convert;
    }

    @Override
    public QuesResponse detail(String id) {
        Ques ques = this.getById(id);
        QuesResponse quesResponse = new QuesResponse();
        BeanUtil.copyProperties(ques, quesResponse);
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenId, ques.getCreateBy()).eq(User::getStatus, CommonStatusEnum.YES.getCode());
        User user = userService.getOne(queryWrapper);
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        quesResponse.setUserResponse(userResponse);
        return quesResponse;
    }
}
