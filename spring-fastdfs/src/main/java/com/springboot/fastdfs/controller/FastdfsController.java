package com.springboot.fastdfs.controller;

import com.springboot.fastdfs.service.FastdfsService;
import org.csource.fastdfs.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/1
 */
@RestController
@RequestMapping("/fast")
public class FastdfsController {

    @Autowired
    FastdfsService fastdfsService;

    @PostMapping("/upload")
    public String uploadFile2Fastdfs(@RequestParam("file") MultipartFile file) {
        try {
            return fastdfsService.uploadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }

    @GetMapping("/info")
    public Map<String, Object> getFileInfo(@RequestParam("filePath") String filePath) {
        return fastdfsService.getFileInfo(filePath);
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam("filePath") String filePath, HttpServletResponse response) {
        fastdfsService.downloadFile(filePath, response);
    }

}
