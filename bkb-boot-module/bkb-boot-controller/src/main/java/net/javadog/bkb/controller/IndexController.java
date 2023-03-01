package net.javadog.bkb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 首页控制器
 *
 * @author: hdx
 * @Date: 2023-01-18 13:57
 * @version: 1.0
 **/
@Api(tags = "首页控制器")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    @ApiOperation(value = "首页列表数据", notes = "首页列表数据")
    @GetMapping("/list")
    public ResponseData list() {
        return ResponseData.success(indexService.indexList());
    }
}
