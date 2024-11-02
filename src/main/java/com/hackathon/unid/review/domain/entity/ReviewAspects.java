package com.hackathon.unid.review.domain.entity;

import com.hackathon.unid.common.base.BaseEntity;
import com.hackathon.unid.common.enums.AspectType;
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
public class ReviewAspects extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewAspectIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "reviewIdx")
    private Reviews review;

    @Enumerated(EnumType.STRING)
    private AspectType aspectType;

    @Size(max = 5)
    @Column(nullable = false)
    private Integer aspectRating;

    @Size(max = 600)
    @Column(nullable = false)
    private String aspectContent;
}
