package com.hackathon.unid.property.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String buildingName;

    private String x;
    private String y;

    @Builder
    public Location(String address, String buildingName, String x, String y) {
        this.address = address;
        this.buildingName = buildingName;
        this.x = x;
        this.y = y;
    }
}
