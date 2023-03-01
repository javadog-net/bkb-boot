package net.javadog.bkb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.bkb.dto.request.QuesRequest;
import net.javadog.bkb.dto.response.QuesResponse;
import net.javadog.bkb.entity.Ques;

/**
 * 提问接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface QuesService extends IService<Ques> {

    void save(QuesRequest quesRequest);

    IPage<QuesResponse> page(QuesRequest quesRequest, Integer current, Integer  size);

    QuesResponse detail(String id);
}
