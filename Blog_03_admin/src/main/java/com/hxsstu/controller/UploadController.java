package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.exception.SystemException;
import com.hxsstu.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: UploadController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-11:30
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile){
        try {
            return uploadService.uploadImg(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }
}
