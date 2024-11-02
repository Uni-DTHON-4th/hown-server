package com.hackathon.unid.record.domain.entity;

import com.hackathon.unid.common.base.BaseEntity;
import com.hackathon.unid.property.domain.entity.Properties;
import com.hackathon.unid.user.domain.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public Records(Users user, Properties property, Integer waterPressure, Integer soundProofing, Integer dayLighting, Integer heating, Integer security,
                    Integer facility, String recordImage, String memo, Double totalRating) {
        this.user = user;
        this.property = property;
        this.waterPressure = waterPressure;
        this.soundProofing = soundProofing;
        this.dayLighting = dayLighting;
        this.heating = heating;
        this.security = security;
        this.facility = facility;
        this.recordImage = recordImage;
        this.memo = memo;
        this.totalRating = totalRating;
    }
}
