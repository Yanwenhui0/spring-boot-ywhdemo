package com.springboot.fastdfs.service;

import com.google.common.collect.ImmutableMap;
import com.springboot.fastdfs.Entity.FastDFSFile;
import com.springboot.fastdfs.util.FastdfsUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Service;
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
@Service
public class FastdfsService {

    public String uploadFile(MultipartFile file) throws Exception {

        String fileName=file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=file.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile fastDFSFile = new FastDFSFile(fileName, ext, file_buff);

        String upload = FastdfsUtils.upload(fastDFSFile);

        //TODO 这里可以追加一些业务代码，例如上传成功后保存到upload_file表，统一进行上传文件管理之类
        return upload;
    }

    public Map<String, Object> getFileInfo(String filePath) {
        FileInfo fileInfo = FastdfsUtils.getFile(filePath);
        NameValuePair[] fileMetadata = FastdfsUtils.getFileMetadata(filePath);
        return ImmutableMap.of("fileInfo", fileInfo, "fileMetadata", fileMetadata);
    }

    public void downloadFile(String filePath, HttpServletResponse response) {
        InputStream inputStream = FastdfsUtils.downFile(filePath);
        NameValuePair[] fileMetadata = FastdfsUtils.getFileMetadata(filePath);
        String fileName = "";
        if(null != fileMetadata && fileMetadata.length > 0) {
            for (NameValuePair fileMetadatum : fileMetadata) {
                if(StringUtils.equals("fileName", fileMetadatum.getName())) {
                    fileName = fileMetadatum.getValue();
                }
            }
        }
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"utf8"));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            count = inputStream.read(buffer);
            while (count > 0) {
                outputStream.write(buffer, 0, count);
                count = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
