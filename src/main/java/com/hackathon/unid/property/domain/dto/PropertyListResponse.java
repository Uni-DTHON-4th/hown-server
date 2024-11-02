package com.hackathon.unid.property.domain.dto;

import java.util.List;

public record PropertyListResponse(List<PropertyDto> propertyList) {
    public record PropertyDto(Long propertyIdx,
                              String x,
                              String y) {}
}
