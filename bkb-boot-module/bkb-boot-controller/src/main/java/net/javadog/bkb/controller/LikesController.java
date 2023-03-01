package net.javadog.bkb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.dto.request.LikesRequest;
import net.javadog.bkb.service.LikesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 点赞控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "点赞控制器")
@RestController
@RequestMapping("/likes")
public class LikesController {

    @Resource
    private LikesService LikesService;

    @ApiOperation(value = "点赞新增", notes = "点赞新增")
    @PostMapping
    public ResponseData save(@RequestBody LikesRequest likesRequest){
        LikesService.save(likesRequest);
        return ResponseData.success();
    }

}
