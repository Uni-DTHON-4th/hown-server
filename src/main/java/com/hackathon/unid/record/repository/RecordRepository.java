package com.hackathon.unid.record.repository;

import com.hackathon.unid.record.domain.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Records, Long> {
    List<Records> findTop5ByUidAndStatusEqualsOrderByCreatedDateDesc(Long uid, String status);
}
