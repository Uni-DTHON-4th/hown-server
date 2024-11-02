package com.hackathon.unid.record.domain.dto;

public record RecordResponse(Long recordIdx,
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
