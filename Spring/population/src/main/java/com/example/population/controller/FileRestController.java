package com.example.population.controller;

import com.example.population.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FileRestController {
    private final FileService fileService;

    @DeleteMapping(value = "/board/file/delete/{fileId}")
    public Long deleteFile(@PathVariable Long fileId){
        fileService.delete(fileId);
        return fileId;
    }
}
