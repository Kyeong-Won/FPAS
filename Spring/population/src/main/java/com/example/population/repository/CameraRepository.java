package com.example.population.repository;

import com.example.population.domain.Camera;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Data
public class CameraRepository {

    private final EntityManager em;

    public void save(Camera camera){
        em.persist(camera);
    }

    public Camera findOne(Long id){
        return em.find(Camera.class, id);
    }
}

