package com.example.population.service;

import com.example.population.domain.Camera;
import com.example.population.elasticsearch.ElasticSearchRepository;
import com.example.population.repository.CameraRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Data
@Transactional(readOnly = true)
public class CameraService {

    private final CameraRepository cameraRepository;
    private final ElasticSearchRepository elasticSearchRepository;

    @Transactional
    public void saveCamera(Camera camera){
        cameraRepository.save(camera);
        elasticSearchRepository.save(camera);
    }

}
