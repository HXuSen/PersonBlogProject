package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: UploadController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-10:34
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
