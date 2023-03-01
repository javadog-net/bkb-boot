package net.javadog.bkb.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author: hdx
 * @Date: 2023-01-13 13:53
 * @version: 1.0
 **/
@Data
public class UserRequest {

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    @ApiModelProperty(value = "openId")
    private String openId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "简介")
    private String intro;

}
