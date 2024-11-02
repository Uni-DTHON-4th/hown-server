package com.hackathon.unid.record.domain.dto;

import java.util.List;

public record RecordListResponse(List<RecordDto> records) {
    public record RecordDto(Long recordIdx,
                            String recordImage,
                            String buildingName,
                            String address,
                            Double totalRating) {}
}
