package net.javadog.bkb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.bkb.common.enums.CommonStatusEnum;
import net.javadog.bkb.common.enums.ServiceErrorEnum;
import net.javadog.bkb.common.exception.ServiceException;
import net.javadog.bkb.common.shiro.util.SubjectUtil;
import net.javadog.bkb.dto.request.LikesRequest;
import net.javadog.bkb.entity.*;
import net.javadog.bkb.mapper.LikesMapper;
import net.javadog.bkb.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 点赞接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:55
 * @version: 1.0
 **/
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements LikesService {

    @Resource
    private RoastService roastService;

    @Resource
    private RoastReplyService roastReplyService;

    @Resource
    private QuesService quesService;

    @Resource
    private QuesReplyService quesReplyService;

    @Override
    public void save(LikesRequest likesRequest) {
        LambdaQueryWrapper<Likes> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Likes::getRelationId, likesRequest.getRelationId())
                .eq(Likes::getCreateBy, SubjectUtil.getUserOpenId())
                .eq(Likes::getStatus, CommonStatusEnum.YES.getCode());
        Likes likes = this.getOne(queryWrapper);
        if(ObjectUtil.isNotNull(likes)){
            int likesNum = likes.getLikesNum();
            if(likesNum == 5){
                throw new ServiceException(ServiceErrorEnum.LIKES_MORE_THEN_FIVE_ERROR);
            }
            likes.setLikesNum(likes.getLikesNum()+1);
            this.updateById(likes);
        }else{
            Likes newLikes = new Likes();
            BeanUtil.copyProperties(likesRequest, newLikes);
            newLikes.setLikesNum(1);
            newLikes.setStatus(CommonStatusEnum.YES.getCode());
            this.save(newLikes);
        }
        byte relationType = likesRequest.getRelationType();
        switch (relationType){
            case 0:
                Roast roast = roastService.getById(likesRequest.getRelationId());
                roast.setLikes(roast.getLikes()+1);
                roast.setHeats(roast.getHeats()+3);
                roastService.updateById(roast);
                break;
            case 1:
                Ques ques = quesService.getById(likesRequest.getRelationId());
                ques.setLikes(ques.getLikes()+1);
                quesService.updateById(ques);
                break;
            case 2:
                RoastReply roastReply = roastReplyService.getById(likesRequest.getRelationId());
                roastReply.setLikes(roastReply.getLikes()+1);
                roastReplyService.updateById(roastReply);
                break;
            case 3:
                QuesReply quesReply = quesReplyService.getById(likesRequest.getRelationId());
                quesReply.setLikes(quesReply.getLikes()+1);
                quesReplyService.updateById(quesReply);
                break;
        }
    }
}
