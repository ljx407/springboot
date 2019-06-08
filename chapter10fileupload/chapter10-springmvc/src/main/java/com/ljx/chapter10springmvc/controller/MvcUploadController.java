package com.ljx.chapter10springmvc.controller;

import com.ljx.chapter10springmvc.validations.MvcUploadValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/mvc")
public class MvcUploadController {

    @Autowired
    private MvcUploadValidation mvcUploadValidation ;

    // 不能使用Valid来验证@PathVariable
    @InitBinder
    public void initDataBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
        webDataBinder.addValidators(mvcUploadValidation);
    }

    @GetMapping("/toUpload/{type}")
    public String toUpload(@PathVariable("type") String type , Model model) {

        if("multi".equalsIgnoreCase(type)) {
            model.addAttribute("actionUrl","/mvc/upload");
        } else if("part".equalsIgnoreCase(type)) {
            model.addAttribute("actionUrl","/mvc/uploadpart");
        } else {
            model.addAttribute("actionUrl","/mvc/uploadreq");
        }
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("uploadfile") MultipartFile file) {
        try {
            String dest = file.getOriginalFilename();
            File destFile = new File(dest);
            file.transferTo(destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping("/uploadpart")
    public String uploadPart(@RequestParam("uploadfile") Part file) {
        String submittedFileName = file.getSubmittedFileName();
        try {
            file.write(submittedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping("/uploadreq")
    public String uploadreq(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = null ;
        if(request instanceof MultipartHttpServletRequest ) {
            multipartHttpServletRequest = (MultipartHttpServletRequest)request;

            try {
                Part uploadfile = multipartHttpServletRequest.getPart("uploadfile");
                uploadfile.write(uploadfile.getSubmittedFileName());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
