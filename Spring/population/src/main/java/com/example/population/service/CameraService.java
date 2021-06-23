package com.example.population.service;

import com.example.population.domain.Camera;
import com.example.population.repository.ElasticSearchRepository;
import com.example.population.repository.CameraRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
