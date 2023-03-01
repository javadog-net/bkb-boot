package net.javadog.bkb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 鉴权控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "鉴权控制器")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户鉴权", notes = "用户鉴权")
    @GetMapping
    public ResponseData auth(@RequestParam String code) throws WxErrorException {
        return ResponseData.success(userService.auth(code));
    }
}
