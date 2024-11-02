package com.hackathon.unid.record.repository;

import com.hackathon.unid.record.domain.entity.Records;
import com.hackathon.unid.user.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Records, Long> {
    List<Records> findTop5ByUserAndStatusEqualsOrderByCreatedDateDesc(Users user, String status);
}
