package com.hackathon.unid.record.application;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.record.repository.RecordRepository;
import com.hackathon.unid.record.domain.dto.RecordListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hackathon.unid.common.constants.Constant.ACTIVE;
import static com.hackathon.unid.common.enums.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    // 기록 목록 조회
    public RecordListResponse getRecords(Long uid) throws BaseException {
        try {
           List<RecordListResponse.RecordDto> recordList = recordRepository.findTop5ByUidAndStatusEqualsOrderByCreatedDateDesc(uid, ACTIVE).stream()
                    .map(record -> new RecordListResponse.RecordDto(
                            record.getRecordIdx(),
                            record.getRecordImage(),
                            record.getProperty().getLocation().getBuildingName(),
                            record.getProperty().getLocation().getAddress(),
                            record.getTotalRating(),
                            record.getWaterPressure(), record.getSoundProofing(), record.getDayLighting(),
                            record.getHeating(), record.getSecurity(), record.getFacility(),
                            record.getMemo())).toList();
            return new RecordListResponse(recordList);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
