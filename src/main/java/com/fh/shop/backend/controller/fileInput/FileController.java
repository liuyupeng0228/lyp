package com.fh.shop.backend.controller.fileInput;

import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.backend.util.FileUtil;
import com.fh.shop.backend.util.SystemContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/fileInput")
public class FileController extends BaseController {

    @RequestMapping(value = "addHeadPortrait", method = RequestMethod.POST)
    public ServerResponse addHeadPortrait(@RequestParam MultipartFile headImage, HttpServletRequest request){
        //输入流
        InputStream inputStream = null;
        try {
            inputStream = headImage.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件名
        String filename = headImage.getOriginalFilename();
        //调用工具类,创建文件路径
        String fileName = FileUtil.copyFile(inputStream, filename, getRootPath(request) + SystemContext.IMAGES_PATH);
        return ServerResponse.successData(ResponseEnum.SUCCESS,SystemContext.IMAGES_PATH+fileName);
    }
}
