package net.javadog.bkb.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 吐槽返回实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class RoastResponse {

    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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
     * 浏览量
     */
    private int views;

    /**
     * 点赞量
     */
    private int likes;

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

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户信息
     */
    private UserResponse userResponse;
}
