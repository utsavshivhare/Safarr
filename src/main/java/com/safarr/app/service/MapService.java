package com.safarr.app.service;

import com.safarr.app.entity.Map;

import java.util.List;

public interface MapService {

    Map saveMap(Map map);
    Map getMapById(Long mapId);
    List<Map> getAllMaps();

}
