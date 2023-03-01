package net.javadog.bkb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.dto.request.RoastRequest;
import net.javadog.bkb.dto.response.RoastResponse;
import net.javadog.bkb.service.RoastService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 吐槽控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "吐槽控制器")
@RestController
@RequestMapping("/roast")
public class RoastController {

    @Resource
    private RoastService roastService;

    @ApiOperation(value = "吐槽新增", notes = "吐槽新增")
    @PostMapping
    public ResponseData save(@RequestBody RoastRequest roastRequest){
        roastService.save(roastRequest);
        return ResponseData.success();
    }

    @ApiOperation(value = "吐槽分页列表", notes = "吐槽分页列表")
    @GetMapping
    public ResponseData get(final RoastRequest roastRequest,
                            final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                            final @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        IPage<RoastResponse> page = roastService.page(roastRequest, current, size);
        return ResponseData.success(page);
    }

    @ApiOperation(value = "吐槽详情", notes = "吐槽详情")
    @GetMapping("/{roastId}")
    public ResponseData detail(@PathVariable String roastId) {
        return ResponseData.success(roastService.detail(roastId));
    }

}
