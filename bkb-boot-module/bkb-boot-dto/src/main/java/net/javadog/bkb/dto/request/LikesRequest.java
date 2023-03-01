package net.javadog.bkb.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 提问请求实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class LikesRequest {

    @ApiModelProperty(value = "关联id")
    @NotBlank(message = "请输入关联id")
    private Long relationId;

    @ApiModelProperty(value = "点赞类型0-吐槽；1-提问；2-吐槽回复；3-提问回复")
    @NotBlank(message = "点赞类型0-吐槽；1-提问；2-吐槽回复；3-提问回复")
    private byte relationType;

    @ApiModelProperty(value = "点赞数")
    @NotBlank(message = "点赞数")
    private int likesNum;

}
