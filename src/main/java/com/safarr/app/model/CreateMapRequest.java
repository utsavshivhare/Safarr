package com.safarr.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMapRequest {
    private String title;
    private List<LocationRequest> locations;  // List of locations
}