package com.safarr.app.controller;

import com.safarr.app.entity.SharedMap;
import com.safarr.app.service.SharedMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SharedMapController {

    @Autowired
    private SharedMapService sharedMapService;

    @PostMapping("/shared-maps")
    public ResponseEntity<SharedMap> shareMap(@RequestBody SharedMap sharedMap) {
        return ResponseEntity.ok(sharedMapService.saveSharedMap(sharedMap));
    }
}