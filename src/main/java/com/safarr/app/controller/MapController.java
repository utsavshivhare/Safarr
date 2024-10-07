package com.safarr.app.controller;

import com.safarr.app.model.Map; // Ensure you import your custom Map class
import com.safarr.app.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    @Autowired
    private MapRepository mapRepository;

    @PostMapping
    public ResponseEntity<Map> createMap(@RequestBody Map map) {
        Map savedMap = mapRepository.save(map);
        return ResponseEntity.ok(savedMap);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Map>> getMapsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(mapRepository.findAllByUserId(userId));
    }
}
