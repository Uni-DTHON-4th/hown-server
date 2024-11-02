package com.hackathon.unid.user.repository;

import com.hackathon.unid.user.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserIdx(Long userIdx);

}
