package com.example.population.controller;

import com.example.population.domain.Camera;
import com.example.population.service.CameraService;
import com.example.population.service.ElasticSearchService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@Slf4j
public class CameraController {

    private final CameraService cameraService;
    private final ElasticSearchService elasticSearchService;
    @GetMapping("/test")
    public String demo(@RequestParam(value = "place")String place, @RequestParam(value = "num")Integer num){
        Camera camera = new Camera();
        camera.init(place, num);
        cameraService.saveCamera(camera);
        elasticSearchService.delete(place); //일단 해당 place 데이터 개수 찾는 쿼리까진 만들어놓음 -> 이후 몇개 이상이면 삭제??
        return "redirect:/";
    }
}
