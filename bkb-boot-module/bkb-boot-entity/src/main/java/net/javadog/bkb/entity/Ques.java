package net.javadog.bkb.entity;

import lombok.Data;
import net.javadog.bkb.common.entity.BaseEntity;

/**
 * 提问实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class Ques extends BaseEntity {

    /**
     * 问题标题
     */
    private String quesTitle;

    /**
     * 问题描述
     */
    private String quesDesc;

    /**
     * 回复数
     */
    private int replyNum;

    /**
     * 点赞量
     */
    private int likes;

    /**
     * 状态 1：正常；0：删除
     */
    private Integer status;
}
