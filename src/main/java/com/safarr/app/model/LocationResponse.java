package com.safarr.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponse {
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private String customText;
}