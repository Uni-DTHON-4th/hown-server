package com.hackathon.unid.property.repository;

import com.hackathon.unid.property.domain.entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Properties, Long> {
    List<Properties> findAllByStatusEqualsOrderByCreatedDateDesc(String status);
}
