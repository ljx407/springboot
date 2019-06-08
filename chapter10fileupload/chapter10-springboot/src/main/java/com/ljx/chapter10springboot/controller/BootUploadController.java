package com.ljx.chapter10springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/boot")
public class BootUploadController {

    @GetMapping("/toUpload")
    public String toUpload() {

        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("fileupload") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        File destFile = new File(originalFilename);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
