package com.safarr.app.repository;

import com.safarr.app.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
}
