package net.javadog.bkb.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 点赞返回实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class LikesResponse {

    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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
