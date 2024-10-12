package com.safarr.app.service;

import com.safarr.app.entity.SharedMap;
import com.safarr.app.repository.SharedMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharedMapService {

    @Autowired
    private SharedMapRepository sharedMapRepository;

    public SharedMap saveSharedMap(SharedMap sharedMap) {
        return sharedMapRepository.save(sharedMap);
    }
}