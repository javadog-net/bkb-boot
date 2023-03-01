package net.javadog.bkb.entity;

import lombok.Data;
import net.javadog.bkb.common.entity.BaseEntity;

/**
 * 问题回复实体
 *
 * @author: hdx
 * @Date: 2023-01-17 11:45
 * @version: 1.0
 **/
@Data
public class QuesReply extends BaseEntity {

    /**
     * 问题id
     */
    private Long quesId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 点赞数
     */
    private int likes;

    /**
     * 状态 1：正常；0：删除
     */
    private Integer status;
}
