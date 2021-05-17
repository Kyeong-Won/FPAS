package com.example.population.service;

import com.example.population.domain.Board;
import com.example.population.domain.Files;
import com.example.population.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FileService {

    private final FileRepository fileRepository;

    public Files toFileEntity(MultipartFile givenFile) throws IOException{
        Files image = new Files();

        // 주어진 업로드된 파일의 확장자 추가
        String sourceFileName = givenFile.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();

        // 주어진 업로드된 파일을 저장할 위치 설
        File destinationFile;
        String destinationFileName;
        String absolutePath = new File("").getAbsolutePath() + "";
        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(absolutePath+"/images/" + destinationFileName);
        } while (destinationFile.exists());
        destinationFile.getParentFile().mkdirs();

        givenFile.transferTo(destinationFile);

        image.setFilename(destinationFileName);
        image.setFileoriName(sourceFileName);
        image.setFileurl("images/" + destinationFileName);

        return image;
    }
    public void save(Files file) throws IOException {
        fileRepository.save(file);
    }

    @Transactional
    public Files findById(Long fileId){
        return fileRepository.findById(fileId).orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다. id=" + fileId));
    }
}
