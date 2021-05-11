package com.example.population.controller;

import com.example.population.domain.Camera;
import com.example.population.service.CameraService;
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

    @GetMapping("/test")
    public String demo(@RequestParam(value = "place")String place, @RequestParam(value = "num")Integer num){
        Camera camera = new Camera();
        camera.init(place, num);
        cameraService.saveCamera(camera);
        return "redirect:/";
    }
}
