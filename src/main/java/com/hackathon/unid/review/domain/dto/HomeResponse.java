package com.hackathon.unid.review.domain.dto;

import java.util.List;

public record HomeResponse(
        List<ReviewDto> reviewList) {
    public record ReviewDto(Long reviewIdx,
                            String reviewImage,
                            Double totalRating,
                            String buildingName,
                            String address){}
}
