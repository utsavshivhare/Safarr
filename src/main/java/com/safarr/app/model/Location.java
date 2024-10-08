package com.safarr.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private String customText;

    @ManyToOne
    @JoinColumn(name = "map_id")
    private Map map;

    @OneToMany(mappedBy = "location")
    private List<Image> images;
}