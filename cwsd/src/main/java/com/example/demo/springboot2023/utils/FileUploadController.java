package com.example.demo.springboot2023.utils;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

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

    @PostMapping("/upload2")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        try {
            Path uploadPath = Paths.get("path/to/your/upload/directory");
            Files.copy(file.getInputStream(), uploadPath.resolve(file.getOriginalFilename()));
            result.success("File uploaded successfully");
            return result;
        } catch (Exception e) {
            result.fail("上传失败");
            //return ResponseEntity.status(500).body("Failed to upload file");
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getImages")
    //@GetMapping("/images/{filename:.+}")
    public Result getImage( String filename) {
            System.out.println(filename);
        Result result = new Result();
        try {
            Path filePath = Paths.get("D:\\User\\Workspace\\VueProjects\\Charpter1\\xmut-web2\\public\\img\\user\\52841cb4b558d5261e0681ab79ccda46").resolve(filename);
            Resource resource = new FileSystemResource(filePath.toFile());
            if (resource.exists()) {
                result.setData(resource);
                return result;
                //return ResponseEntity.ok().body(resource);
            } else {
                result.fail("文件不存在");
                //return ResponseEntity.notFound().build();
                return result;
            }
        } catch (Exception e) {
            result.fail("下载失败");
            //return ResponseEntity.status(500).body("Failed to upload file");
            return result;
            //return ResponseEntity.status(500).build();
        }
    }
}
