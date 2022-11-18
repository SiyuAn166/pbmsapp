package com.petrobest.pbmsapp.system.controller;

import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.common.utils.FileuploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/system/upload")
public class FileUploadController extends BaseController {
    /**
     * 方法描述：文件上传，图片也可以使用，但是图片不会被压缩.
     * 创建时间：2018-10-19 14:10:32
     *
     * @param childFile 上传的父目录
     * @param extension 允许上传的文件后缀名
     *
     */
    @PostMapping("/file")
    public Object uploadFile(
            MultipartFile multipart,
            @RequestParam(value = "childFile", required = false, defaultValue = "") String childFile,
            @RequestParam(value = "extension", required = false, defaultValue = "") String extension
    ) throws IOException {
        return ResponseBo.ok(FileuploadUtils.saveFile(multipart, childFile, extension));
    }

    /**
     * 方法描述：图片上传，只能给图片使用，其他文件调用会异常.
     * 创建时间：2018-10-19 14:10:32
     *
     * @param childFile 上传的父目录
     * @param extension 允许上传的文件后缀名
     *
     */
    @PostMapping("/image")
    public Object uploadImage(
            MultipartFile multipart,
            @RequestParam(value = "childFile", required = false, defaultValue = "") String childFile,
            @RequestParam(value = "extension", required = false, defaultValue = "") String extension
    ) throws IOException {
        return ResponseBo.ok(FileuploadUtils.saveImage(multipart, childFile, extension));
    }

}
