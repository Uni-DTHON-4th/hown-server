package com.hackathon.unid.record.domain.dto;

public record RecordRequest(Long propertyIdx,
                            Integer waterPressure,
                            Integer soundProofing,
                            Integer dayLighting,
                            Integer heating,
                            Integer security,
                            Integer facility,
                            String memo,
                            Integer totalRating) {
}
