package com.safarr.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapResponse {
    private Long id;
    private String title;
    private List<LocationResponse> locations;
}
