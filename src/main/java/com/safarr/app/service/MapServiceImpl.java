package com.safarr.app.service;

import com.safarr.app.entity.AppUser;
import com.safarr.app.entity.Map;
import com.safarr.app.exceptionhandler.AppException;
import com.safarr.app.repository.AppUserRepository;
import com.safarr.app.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public Map saveMap(Map map) {
        map.setUser(getLoggedInUserDetails());
        return mapRepository.save(map);
    }

    @Override
    public Map getMapById(Long mapId) {
        return mapRepository.findByIdAndUserId(mapId, getLoggedInUserDetails().getId())
                .orElseThrow(() ->
                        new AppException("Map not found with id:" + mapId, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Map> getAllMaps() {
        return mapRepository.findAllByUserId(getLoggedInUserDetails().getId());
    }

    private AppUser getLoggedInUserDetails() {
        String usernameOrEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return appUser;
    }


}