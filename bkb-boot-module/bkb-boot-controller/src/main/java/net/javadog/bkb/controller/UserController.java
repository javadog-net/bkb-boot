package net.javadog.bkb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.dto.request.UserRequest;
import net.javadog.bkb.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户详情", notes = "用户详情")
    @GetMapping
    public ResponseData detail(){
        return ResponseData.success(userService.detail());
    }

    @ApiOperation(value = "用户更新", notes = "用户更新")
    @PutMapping
    public ResponseData update(@RequestBody UserRequest userRequest){
        userService.update(userRequest);
        return ResponseData.success();
    }
}
