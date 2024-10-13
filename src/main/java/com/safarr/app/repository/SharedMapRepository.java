package com.safarr.app.repository;

import com.safarr.app.entity.SharedMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedMapRepository extends JpaRepository<SharedMap, Long> {
}