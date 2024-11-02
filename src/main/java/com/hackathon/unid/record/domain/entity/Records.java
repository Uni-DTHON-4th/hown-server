package com.hackathon.unid.record.domain.entity;

import com.hackathon.unid.common.base.BaseEntity;
import com.hackathon.unid.property.domain.entity.Properties;
import com.hackathon.unid.user.domain.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Records extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userIdx")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "propertyIdx")
    private Properties property;

    @Size(max = 5)
    @Column(nullable = false)
    private Integer waterPressure;

    @Size(max = 5)
    @Column(nullable = false)
    private Integer soundProofing;

    @Size(max = 5)
    @Column(nullable = false)
    private Integer dayLighting;

    @Size(max = 5)
    @Column(nullable = false)
    private Integer heating;

    @Size(max = 5)
    @Column(nullable = false)
    private Integer security;

    @Size(max = 5)
    @Column(nullable = false)
    private Integer facility;

    @Column(nullable = false)
    private String recordImage;

    @Column(nullable = false, length = 1000)
    private String memo;

    private Double totalRating;
}
