package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.one.domain.Res;
import top.one.service.UploadService;

/**
 * @author: XiaoWan
 * @Date: 2022/12/5
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public Res uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
