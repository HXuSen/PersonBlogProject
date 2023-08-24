package com.hxsstu.service;

import com.hxsstu.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: UploadService
 * Package: com.hxsstu.service
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-10:35
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
