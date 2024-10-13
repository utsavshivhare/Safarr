package com.safarr.app.controller;

import static com.safarr.app.utils.Utils.mapEntitiesToResponses;
import static com.safarr.app.utils.Utils.mapEntityToResponse;
import static com.safarr.app.utils.Utils.mapRequestToEntity;


import com.safarr.app.model.CreateMapRequest;
import com.safarr.app.model.MapResponse;
import com.safarr.app.model.MapResponseWrapper;
import com.safarr.app.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MapController {

    @Autowired
    private MapService mapService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/maps", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MapResponse> createMap(@RequestBody @Valid CreateMapRequest createMapRequest) {
        return ResponseEntity.ok(mapEntityToResponse(mapService.saveMap(mapRequestToEntity(createMapRequest))));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "maps", produces = "application/json")
    public ResponseEntity<MapResponseWrapper> getMaps() {
        return ResponseEntity.ok(MapResponseWrapper
                .builder()
                .maps(mapEntitiesToResponses(mapService.getAllMaps()))
                .build());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "maps/{mapId}", produces = "application/json")
    public ResponseEntity<MapResponse> getMapById(@PathVariable Long mapId) {
        return ResponseEntity.ok(mapEntityToResponse(mapService.getMapById(mapId)));
    }
}
