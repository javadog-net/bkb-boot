package net.javadog.bkb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.dto.request.QuesRequest;
import net.javadog.bkb.dto.response.QuesResponse;
import net.javadog.bkb.service.QuesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 提问控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "提问控制器")
@RestController
@RequestMapping("/ques")
public class QuesController {

    @Resource
    private QuesService quesService;

    @ApiOperation(value = "提问新增", notes = "提问新增")
    @PostMapping
    public ResponseData save(@RequestBody QuesRequest quesRequest){
        quesService.save(quesRequest);
        return ResponseData.success();
    }

    @ApiOperation(value = "提问分页列表", notes = "提问分页列表")
    @GetMapping
    public ResponseData get(final QuesRequest quesRequest,
                            final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                            final @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        IPage<QuesResponse> page = quesService.page(quesRequest, current, size);
        return ResponseData.success(page);
    }


    @ApiOperation(value = "提问详情", notes = "提问详情")
    @GetMapping("/{quesId}")
    public ResponseData detail(@PathVariable String quesId) {
        return ResponseData.success(quesService.detail(quesId));
    }

}
