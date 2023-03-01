package net.javadog.bkb.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.bkb.dto.request.RoastReplyRequest;
import net.javadog.bkb.dto.response.RoastReplyResponse;
import net.javadog.bkb.entity.RoastReply;

/**
 * 吐槽回复接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface RoastReplyService extends IService<RoastReply> {

    void save(RoastReplyRequest roastReplyRequest);

    IPage<RoastReplyResponse> page(RoastReplyRequest roastReplyRequest, Integer current, Integer  size);

}
