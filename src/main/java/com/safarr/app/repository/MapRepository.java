package com.safarr.app.repository;

import com.safarr.app.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {

    List<Map> findAllByUserId(Long userId);
}
