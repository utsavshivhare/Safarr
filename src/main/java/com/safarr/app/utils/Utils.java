package com.safarr.app.utils;

import com.safarr.app.entity.AppUser;
import com.safarr.app.entity.Location;
import com.safarr.app.entity.Map;
import com.safarr.app.model.CreateMapRequest;
import com.safarr.app.model.LocationRequest;
import com.safarr.app.model.LocationResponse;
import com.safarr.app.model.MapResponse;
import com.safarr.app.model.RegisterUserRequest;
import com.safarr.app.repository.AppUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static AppUser userRequestToEntity(RegisterUserRequest registerUserRequest) {
        return AppUser.builder()
                .username(registerUserRequest.getUsername())
                .email(registerUserRequest.getEmail())
                .password(registerUserRequest.getPassword())
                .firstName(registerUserRequest.getFirstName())
                .lastName(registerUserRequest.getLastName())
                .build();
    }

    public static Map mapRequestToEntity(CreateMapRequest createMapRequest) {
        Map map = Map.builder()
                .title(createMapRequest.getTitle())
                .build();

        if (createMapRequest.getLocations() != null) {
            List<Location> locations = createMapRequest.getLocations().stream()
                    .map(locationRequest -> {
                        Location location = Utils.locationRequestToEntity(locationRequest);
                        location.setMap(map); // Set the map reference in each location so foreign key column get set
                        return location;
                    })
                    .collect(Collectors.toList());
            map.setLocations(locations);
        }
        return map;
    }

    public static Location locationRequestToEntity(LocationRequest locationRequest) {
        return Location.builder()
                .name(locationRequest.getName())
                .latitude(locationRequest.getLatitude())
                .longitude(locationRequest.getLongitude())
                .description(locationRequest.getDescription())
                .customText(locationRequest.getCustomText())
                .build();
    }

    public static MapResponse mapEntityToResponse(Map map) {
        return MapResponse.builder()
                .id(map.getId())
                .title(map.getTitle())
                .locations(map.getLocations() != null ? map.getLocations().stream()
                        .map(Utils::locationEntityToResponse)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public static LocationResponse locationEntityToResponse(Location location) {
        return LocationResponse.builder()
                .name(location.getName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .description(location.getDescription())
                .customText(location.getCustomText())
                .build();
    }

    public static List<MapResponse> mapEntitiesToResponses(List<Map> maps) {
        return maps != null ? maps.stream()
                .map(Utils::mapEntityToResponse)
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    public static AppUser getLoggedInUserDetails(AppUserRepository appUserRepository) {
        String usernameOrEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
