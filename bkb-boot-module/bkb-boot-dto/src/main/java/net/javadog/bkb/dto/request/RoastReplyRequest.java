package net.javadog.bkb.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 吐槽回复请求实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class RoastReplyRequest {

    @ApiModelProperty(value = "吐槽id")
    @NotBlank(message = "请输入针对公司")
    private Long roastId;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "请输入内容")
    private String content;
}
