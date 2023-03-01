package net.javadog.bkb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.dto.request.QuesReplyRequest;
import net.javadog.bkb.dto.response.QuesReplyResponse;
import net.javadog.bkb.service.QuesReplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 问题回复控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "问题回复控制器")
@RestController
@RequestMapping("/quesReply")
public class QuesReplyController {

    @Resource
    private QuesReplyService quesReplyService;

    @ApiOperation(value = "问题回复新增", notes = "问题回复新增")
    @PostMapping
    public ResponseData save(@RequestBody QuesReplyRequest quesReplyRequest){
        quesReplyService.save(quesReplyRequest);
        return ResponseData.success();
    }


    @ApiOperation(value = "问题回复分页列表", notes = "问题回复分页列表")
    @GetMapping
    public ResponseData get(final QuesReplyRequest quesReplyRequest,
                             final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                             final @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        IPage<QuesReplyResponse> page = quesReplyService.page(quesReplyRequest, current, size);
        return ResponseData.success(page);
    }
}
