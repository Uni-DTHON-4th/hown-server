package com.hackathon.unid.property.domain.dto;

import com.hackathon.unid.common.enums.AspectType;

import java.util.List;
import java.util.Map;

public record PropertyResponse(String buildingName,
                               String address,
                               String propertyImage,
                               Map<AspectType, Integer> RatingDto,
                               List<PropertyReviewDto> reviewList) {
    public record PropertyReviewDto(String nickname,
                                    Double totalRating,
                                    String pros,
                                    String cons,
                                    String reviewImage) {}
}
