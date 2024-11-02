package com.hackathon.unid.review.repository;

import com.hackathon.unid.review.domain.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findTop5ByStatusEqualsOrderByCreatedDateDesc(String status);
}