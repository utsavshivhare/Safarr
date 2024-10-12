package com.safarr.app.service;

import com.safarr.app.entity.Map;
import com.safarr.app.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    public Map saveMap(Map map) {
        return mapRepository.save(map);
    }
}