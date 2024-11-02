package com.hackathon.unid.review.domain.entity;

import com.hackathon.unid.common.base.BaseEntity;
import com.hackathon.unid.property.domain.entity.Properties;
import com.hackathon.unid.user.domain.entity.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reviews extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userIdx")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "propertyIdx")
    private Properties property;

    @Column(nullable = false)
    private String reviewImage;

    private Double totalRating;
}
