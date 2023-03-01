package net.javadog.bkb.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 问题回复请求实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class QuesReplyRequest {

    @ApiModelProperty(value = "问题id")
    @NotBlank(message = "请选择回复问题")
    private Long quesId;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "请输入内容")
    private String content;
}
