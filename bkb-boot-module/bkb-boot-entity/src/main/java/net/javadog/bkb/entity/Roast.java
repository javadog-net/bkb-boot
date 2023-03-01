package net.javadog.bkb.entity;

import lombok.Data;
import net.javadog.bkb.common.entity.BaseEntity;

/**
 * 吐槽实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class Roast extends BaseEntity {

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 公司描述
     */
    private String companyDesc;

    /**
     * 公司类型(0-黑名单；1-红名单)
     */
    private byte companyType;

    /**
     * 点赞量
     */
    private int likes;

    /**
     * 浏览量
     */
    private int views;

    /**
     * 评论量
     */
    private int comments;

    /**
     * 热度(浏览+1，评论+5)
     */
    private int heats;

    /**
     * 状态 1：正常；0：删除
     */
    private Integer status;
}
