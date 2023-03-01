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
public class QuesRequest {

    @ApiModelProperty(value = "问题标题")
    @NotBlank(message = "请输入问题标题")
    private String quesTitle;

    @ApiModelProperty(value = "问题描述")
    @NotBlank(message = "请输入问题描述")
    private String quesDesc;

    @ApiModelProperty(value = "是否自己-0-否；1-是")
    private byte mine = 0;

}
