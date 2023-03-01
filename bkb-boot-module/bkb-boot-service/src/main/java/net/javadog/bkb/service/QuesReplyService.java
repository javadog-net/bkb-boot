package net.javadog.bkb.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.bkb.dto.request.QuesReplyRequest;
import net.javadog.bkb.dto.response.QuesReplyResponse;
import net.javadog.bkb.entity.QuesReply;

/**
 * 问题回复接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface QuesReplyService extends IService<QuesReply> {

    void save(QuesReplyRequest quesReplyRequest);

    IPage<QuesReplyResponse> page(QuesReplyRequest quesReplyRequest, Integer current, Integer  size);

}
