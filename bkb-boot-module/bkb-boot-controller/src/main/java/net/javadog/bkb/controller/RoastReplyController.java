package net.javadog.bkb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.dto.request.RoastReplyRequest;
import net.javadog.bkb.dto.response.RoastReplyResponse;
import net.javadog.bkb.service.RoastReplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 吐槽回复控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "吐槽回复控制器")
@RestController
@RequestMapping("/roastReply")
public class RoastReplyController {

    @Resource
    private RoastReplyService roastReplyService;

    @ApiOperation(value = "吐槽回复新增", notes = "吐槽回复新增")
    @PostMapping
    public ResponseData save(@RequestBody RoastReplyRequest roastReplyRequest){
        roastReplyService.save(roastReplyRequest);
        return ResponseData.success();
    }


    @ApiOperation(value = "吐槽回复分页列表", notes = "吐槽回复分页列表")
    @GetMapping
    public ResponseData get(final RoastReplyRequest roastReplyRequest,
                             final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                             final @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        IPage<RoastReplyResponse> page = roastReplyService.page(roastReplyRequest, current, size);
        return ResponseData.success(page);
    }
}
