package net.javadog.bkb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.bkb.dto.request.LikesRequest;
import net.javadog.bkb.entity.Likes;

/**
 * 点赞接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface LikesService extends IService<Likes> {

    void save(LikesRequest likesRequest);

}
