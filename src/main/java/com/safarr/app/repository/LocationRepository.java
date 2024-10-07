package com.safarr.app.repository;

import com.safarr.app.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByMapId(Long mapId);
}
