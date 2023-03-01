package net.javadog.bkb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.bkb.common.exception.ServiceException;
import net.javadog.bkb.common.response.ResponseData;
import net.javadog.bkb.dto.response.FileResponse;
import net.javadog.bkb.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Description: 文件上传
 * @Author: hdx
 * @Date: 2022/2/9 14:22
 * @Version: 1.0
 */
@RestController
@Api(tags = "上传控制器")
public class UploadController {

    @Resource
    private FileService fileService;


    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/upload")
    public ResponseData upload(MultipartFile file) {
        FileResponse fileResponse;
        try {
            fileResponse = fileService.upload(file);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
        return ResponseData.success(fileResponse, "上传成功");
    }
}
