package com.hackathon.unid.user.domain.entity;

import com.hackathon.unid.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Builder
    public Users(String nickname, String loginId, String password) {
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
    }
}
