package com.quiz.controller;

import com.quiz.utils.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * FileConteoller
 * </p>
 *
 * @author XGeorge
 * @since 2024/7/20 上午10:02
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件上传下载相关")
@RequiredArgsConstructor
@Log4j2
public class FileUploadController {

    /**
     * 从配置文件中读取文件存储路径
     */
    @Value("${file.dir}")
    private String dir;

    // 处理文件上传请求
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("文件上传")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }

        // 构建目标文件路径
        String fileName = file.getOriginalFilename();
        Assert.isNotNull(fileName, "文件名不能为空");
        File dest = new File(dir, fileName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        try {
            // 保存文件到目标路径
            file.transferTo(dest);
            return "文件上传成功: " + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败: " + e.getMessage();
        }
    }
    // 处理文件下载请求
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<Resource> handleFileDownload(@PathVariable String fileName) {
//        // 构建目标文件路径
//        File file = new File(downloadDir, fileName);
//        Resource resource = new FileSystemResource(file);
//
//        // 检查文件是否存在
//        if (!file.exists()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//
//        // 设置文件下载的HTTP头
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//
//        // 返回文件资源
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(resource);
//    }
}
