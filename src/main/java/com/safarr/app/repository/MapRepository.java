package com.safarr.app.repository;

import com.safarr.app.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findAllByUserId(Long userId);
    Optional<Map> findByIdAndUserId(Long id, Long userId);
}
