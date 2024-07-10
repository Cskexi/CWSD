package com.example.demo.springboot2023.utils;

import com.example.demo.videos.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    @Autowired
    private VideosService videosService;
    @PostMapping("/upload")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("id") String id,
                                   @RequestParam("type") String type) {
        Result result = new Result();
        String folderPath = "D:\\User\\Workspace\\CWSD\\cwsd-web\\public\\img";
        folderPath += "\\" + type;
        folderPath += "\\" + id;
        File folder = new File(folderPath);
        //System.out.println(id);
        System.out.println(folderPath);
        try {
            if (!folder.exists()) {
                System.out.println("文件夹不存在");
                boolean success = folder.mkdirs();
                if (success) {
                    System.out.println("Folders created successfully");
                } else {
                    System.out.println("Failed to create folders");
                }
            }
            Path path = Paths.get(folderPath).resolve(file.getOriginalFilename());
            Files.write(path, file.getBytes());
            result.setData(path);
            result.success("上传成功");
            return result;
        } catch (Exception e) {
            result.fail("上传失败");
            return result;
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = "/deleteFile")
    public Result deleteFile(@RequestParam("url") String url,
                             @RequestParam("id") String id) {
        Result result = new Result();
        String folderPath = "D:\\User\\Workspace\\CWSD\\cwsd-web\\public\\";
        String filePath = folderPath  + url;
//        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                videosService.deleteByIds(id);
                //Files.delete(path);
                result.success("文件删除成功");
            } else {
                result.fail("文件不存在");
            }
            return result;
 //       }
//        catch (IOException e) {
//            result.fail("删除文件时发生错误");
//            return result;
//        }
    }


}
