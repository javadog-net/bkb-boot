package net.javadog.bkb.entity;

import lombok.Data;
import net.javadog.bkb.common.entity.BaseEntity;

/**
 * 点赞实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class Likes extends BaseEntity {

    /**
     * 关联id
     */
    private Long relationId;

    /**
     * 点赞类型0-吐槽；1-提问；2-吐槽回复；3-提问回复
     */
    private byte relationType;

    /**
     * 点赞数
     */
    private int likesNum;

    /**
     * 状态 1：正常；0：删除
     */
    private Integer status;
}
