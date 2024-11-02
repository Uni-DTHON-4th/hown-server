package com.hackathon.unid.record.domain.dto;

import java.util.List;

public record RecordListResponse(List<RecordDto> records) {
    public record RecordDto(Long recordIdx,
                            String recordImage,
                            String buildingName,
                            String address,
                            Double totalRating,
                            Integer waterPressure,
                            Integer soundProofing,
                            Integer dayLighting,
                            Integer heating,
                            Integer security,
                            Integer facility,
                            String memo) {}
}
