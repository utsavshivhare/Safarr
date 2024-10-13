package com.safarr.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
    @JoinColumn(name = "map_id", referencedColumnName = "id")
    private Map map;

    @OneToMany(mappedBy = "location")
    private List<Image> images;
}