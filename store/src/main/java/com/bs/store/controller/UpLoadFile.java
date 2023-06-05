package com.bs.store.controller;



import com.bs.store.tools.FilePath;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class UpLoadFile {

    @PostMapping("/image")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        String path = FilePath.dir;
        String oldFileName = file.getOriginalFilename();
        String newFileName = res + oldFileName.substring(oldFileName.lastIndexOf("."));
        File newFile = new File(path + newFileName);
        System.out.println(path+newFileName);
        file.transferTo(newFile);
        String realpath = path.substring(path.lastIndexOf("\\images"))+newFileName;
        realpath= realpath.replace('\\','/');
        System.out.println(realpath);
        return realpath;
    }

        //上传商品图片
    @PostMapping("/product/image")
    public void uploadProductImage(@RequestParam("path") String path,@RequestParam("files") MultipartFile[] files) throws IOException {
        String newPath = FilePath.dir;
        String [] profix={"collect.png","1_big.png","2_big.png","3_big.png","4_big.png","5_big.png"};
       for (int i=0;i<files.length;i++){
           String temp = path.substring(path.lastIndexOf("/portal/"));
           String pathName=newPath+temp+profix[i];
           File newFile = new File(pathName);
           if (!newFile.exists()){
               newFile.mkdirs();
           }
           files[i].transferTo(newFile);
       }
    }
}
