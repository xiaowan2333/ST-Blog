package top.one.service;

import org.springframework.web.multipart.MultipartFile;
import top.one.domain.Res;

/**
 * @author: XiaoWan
 * @Date: 2022/12/5
 */
public interface UploadService {
    public Res uploadImg(MultipartFile img);
}
