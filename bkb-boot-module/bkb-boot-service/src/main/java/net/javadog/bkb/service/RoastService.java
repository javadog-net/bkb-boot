package net.javadog.bkb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.bkb.dto.request.RoastRequest;
import net.javadog.bkb.dto.response.RoastResponse;
import net.javadog.bkb.entity.Roast;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 吐槽接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface RoastService extends IService<Roast> {

    void save(RoastRequest roastRequest);

    IPage<RoastResponse> page(RoastRequest roastRequest,Integer current,Integer  size);

    RoastResponse detail(String id);

}
