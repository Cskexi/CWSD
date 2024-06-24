package com.example.cwsd.other.utils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        String folderPath = "D:\\User\\Workspace\\VueProjects\\Charpter1\\xmut-web2\\public\\img";
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
}
