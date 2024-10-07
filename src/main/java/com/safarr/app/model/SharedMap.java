package com.safarr.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "map_id")
    private Map map;

    @ManyToOne
    @JoinColumn(name = "shared_with_user_id")
    private AppUser sharedWithUser;
}

