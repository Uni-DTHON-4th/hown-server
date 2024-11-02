package com.hackathon.unid.review.repository;

import com.hackathon.unid.property.domain.entity.Properties;
import com.hackathon.unid.review.domain.entity.ReviewAspects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewAspectsRepository extends JpaRepository<ReviewAspects, Long> {
    List<ReviewAspects> findAllByReview_PropertyAndReview_StatusEquals(Properties property, String status);

}
