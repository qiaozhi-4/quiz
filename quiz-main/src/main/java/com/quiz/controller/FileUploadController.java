package com.quiz.controller;

import com.quiz.entity.User;
import com.quiz.exception.APIException;
import com.quiz.utils.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("文件上传")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        return fileUpload(file, "");
    }

    @PostMapping(value = "upload-avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("用户上传头像")
    public User uploadAvatar(@RequestParam("file") MultipartFile avatarFile, @RequestParam("userId") Integer userId) {
        User user = User.builder().avatarUrl(fileUpload(avatarFile, "\\avatar")).userId(userId).build();
        user.updateById();
        return user;
    }

    // 这个方法通过文件名返回图片资源
    @GetMapping("download-avatar/{filename:.+}")
    @ApiOperation("获取头像")
    public ResponseEntity<Resource> downloadAvatar(@PathVariable String filename) {
        return filDownload(filename, "\\avatar");
    }

    private ResponseEntity<Resource> filDownload(String filename, String prefix) {
        try {
            Path filePath = Paths.get(dir + prefix).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                // 设置响应头，以便浏览器显示图片而不是下载
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG.toString())
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    private String fileUpload(MultipartFile file, String prefix) {
        Assert.isTrue(!file.isEmpty(), "文件为空");

        // 构建目标文件路径
        String fileName = file.getOriginalFilename();
        Assert.isNotNull(fileName, "文件名不能为空");
        String[] split = fileName.split("\\.");
        split[0] += LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        fileName = String.join(".", split);
        File dest = new File(dir + prefix, fileName);
        try {
            // 保存文件到目标路径
            file.transferTo(dest);
            // 设置文件权限为777
//            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
//            Files.setPosixFilePermissions(Paths.get(dest.getPath()), perms);
            return fileName;
        } catch (IOException e) {
            throw new APIException(e.getMessage());
        }
    }

}
