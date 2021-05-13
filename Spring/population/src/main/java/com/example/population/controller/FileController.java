//package com.example.population.controller;
//
//import com.example.population.domain.Files;
//import com.example.population.service.FileService;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Controller
//public class FileController {
//
//    @Autowired
//    FileService fileService;
//
//    @PostMapping("/boards/board/save")
//    public String savefile(@RequestParam("files") MultipartFile files, @RequestParam("title")String title) throws Exception{
////        Files file = new Files();
////
////        String sourceFileName = files.getOriginalFilename();
////        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
////        File destinationFile;
////        String destinationFileName;
////
////        String absolutePath = new File("").getAbsolutePath() + "";
////        do {
////            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
////            destinationFile = new File(absolutePath+"/images/" + destinationFileName);
////        } while (destinationFile.exists());
////        destinationFile.getParentFile().mkdirs();
//////        System.out.println("absolutePath = " + absolutePath);
//////        System.out.println("destinationFile = " + destinationFile);
//////        System.out.println("destinationFileName = " + destinationFileName);
////
////        files.transferTo(destinationFile);
////
////        file.setFilename(destinationFileName);
////        file.setFileoriName(sourceFileName);
////        file.setFileurl("images/" + destinationFileName);
////        fileService.save(file);
////        return "redirect:/boards/board";
//
//    }
//}
