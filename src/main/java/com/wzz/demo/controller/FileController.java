package com.wzz.demo.controller;


import com.wzz.demo.utils.FtpUtil;
import com.wzz.demo.utils.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FtpUtil ftpUtil;

    @PostMapping("/upload")
    public JSONResult upFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //获取源文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        if (StringUtils.isBlank(fileName)) {
            return JSONResult.errorMsg("文件名为空");
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        InputStream inputStream = file.getInputStream();
        String filePath = null;
        boolean flag = ftpUtil.uploadFile(fileName, inputStream);
        if (!flag) {
            return JSONResult.errorMsg("ftp连接失败");
        }
        filePath = ftpUtil.FTP_BASEPATH + fileName;
        return JSONResult.ok(filePath);
    }

}
