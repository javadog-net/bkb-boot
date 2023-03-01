package net.javadog.bkb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.javadog.bkb.common.enums.CommonStatusEnum;
import net.javadog.bkb.dto.response.IndexListResponse;
import net.javadog.bkb.dto.response.QuesResponse;
import net.javadog.bkb.dto.response.RoastResponse;
import net.javadog.bkb.entity.Ques;
import net.javadog.bkb.entity.Roast;
import net.javadog.bkb.service.IndexService;
import net.javadog.bkb.service.QuesService;
import net.javadog.bkb.service.RoastService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: hdx
 * @Date: 2023-01-13 13:37
 * @version: 1.0
 **/
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private RoastService roastService;

    @Resource
    private QuesService quesService;

    @Override
    public IndexListResponse indexList() {
        IndexListResponse indexListResponse = new IndexListResponse();
        LambdaQueryWrapper<Roast> blackQueryWrapper = new LambdaQueryWrapper<>();
        blackQueryWrapper.eq(Roast::getStatus, CommonStatusEnum.YES.getCode())
                .eq(Roast::getCompanyType, 0)
                .orderByDesc(Roast::getHeats,Roast::getCreateTime)
                .last("limit 3");
        List<Roast> blackList = roastService.list(blackQueryWrapper);
        List<RoastResponse> blackListResponse = CollectionUtil.newArrayList();
        blackList.forEach(item->{
            RoastResponse roastResponse = new RoastResponse();
            BeanUtil.copyProperties(item, roastResponse);
            blackListResponse.add(roastResponse);
        });
        indexListResponse.setBlackList(blackListResponse);

        LambdaQueryWrapper<Roast> redQueryWrapper = new LambdaQueryWrapper<>();
        redQueryWrapper.eq(Roast::getStatus, CommonStatusEnum.YES.getCode())
                .eq(Roast::getCompanyType, 1)
                .orderByDesc(Roast::getHeats,Roast::getCreateTime)
                .last("limit 3");
        List<Roast> redList = roastService.list(redQueryWrapper);
        List<RoastResponse> redListResponse = CollectionUtil.newArrayList();
        redList.forEach(item->{
            RoastResponse roastResponse = new RoastResponse();
            BeanUtil.copyProperties(item, roastResponse);
            redListResponse.add(roastResponse);
        });
        indexListResponse.setRedList(redListResponse);

        LambdaQueryWrapper<Roast> lastQueryWrapper = new LambdaQueryWrapper<>();
        lastQueryWrapper.eq(Roast::getStatus, CommonStatusEnum.YES.getCode())
                .orderByDesc(Roast::getCreateTime)
                .last("limit 3");
        List<Roast> lastList = roastService.list(lastQueryWrapper);
        List<RoastResponse> lastListResponse = CollectionUtil.newArrayList();
        lastList.forEach(item->{
            RoastResponse roastResponse = new RoastResponse();
            BeanUtil.copyProperties(item, roastResponse);
            lastListResponse.add(roastResponse);
        });
        indexListResponse.setLastList(lastListResponse);

        LambdaQueryWrapper<Ques> quesQueryWrapper = new LambdaQueryWrapper<>();
        quesQueryWrapper.eq(Ques::getStatus, CommonStatusEnum.YES.getCode())
                .orderByDesc(Ques::getReplyNum,Ques::getCreateTime)
                .last("limit 3");
        List<Ques> quesList = quesService.list(quesQueryWrapper);
        List<QuesResponse> quesListResponse = CollectionUtil.newArrayList();
        quesList.forEach(item->{
            QuesResponse quesResponse = new QuesResponse();
            BeanUtil.copyProperties(item, quesResponse);
            quesListResponse.add(quesResponse);
        });
        indexListResponse.setQuesList(quesListResponse);
        return indexListResponse;
    }
}
