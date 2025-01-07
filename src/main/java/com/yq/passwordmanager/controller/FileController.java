package com.yq.passwordmanager.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import com.yq.passwordmanager.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${file.avatar-upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    @ResponseBody
    public Result<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.failure(null, "请选择文件！", null);
        }
        try {
            String fileName = saveFile(file);
            return Result.success(fileName, "文件上传成功！", null);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure(null, "文件上传失败！", null);
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        // 创建上传目录（如果不存在）
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            FileUtil.mkdir(uploadDir);
        }

        // 生成唯一文件名
        String fileName = generateFileName(file);

        // 保存文件
        File targetFile = new File(uploadDir + File.separator + fileName);
        IoUtil.copy(file.getInputStream(), FileUtil.getOutputStream(targetFile));

        return fileName;
    }

    private String generateFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 获取文件扩展名
        return UUID.fastUUID().toString(true) + fileExtension; // 生成不带连字符的 UUID 文件名
    }
}