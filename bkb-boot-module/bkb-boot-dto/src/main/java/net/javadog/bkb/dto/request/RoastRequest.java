package net.javadog.bkb.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 吐槽请求实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class RoastRequest {

    @ApiModelProperty(value = "公司名")
    @NotBlank(message = "请输入公司名")
    private String companyName;

    @ApiModelProperty(value = "公司描述")
    @NotBlank(message = "请输入公司描述")
    private String companyDesc;

    @ApiModelProperty(value = "0-黑名单；1-红名单")
    private byte companyType;

    @ApiModelProperty(value = "是否自己-0-否；1-是")
    private byte mine = 0;

}
