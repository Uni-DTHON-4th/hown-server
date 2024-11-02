package com.hackathon.unid.record.application;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.common.gcs.GCSService;
import com.hackathon.unid.property.domain.entity.Properties;
import com.hackathon.unid.property.repository.PropertyRepository;
import com.hackathon.unid.record.domain.dto.RecordRequest;
import com.hackathon.unid.record.domain.dto.RecordResponse;
import com.hackathon.unid.record.domain.entity.Records;
import com.hackathon.unid.record.repository.RecordRepository;
import com.hackathon.unid.record.domain.dto.RecordListResponse;
import com.hackathon.unid.user.domain.entity.Users;
import com.hackathon.unid.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.hackathon.unid.common.constants.Constant.ACTIVE;
import static com.hackathon.unid.common.enums.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final GCSService gcsService;
    private final PropertyRepository propertyRepository;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    // 기록 목록 조회
    public RecordListResponse getRecords(Long uid) throws BaseException {
        try {
            Users user = userRepository.findByUserIdx(uid);
            List<RecordListResponse.RecordDto> recordList = recordRepository.findTop5ByUserAndStatusEqualsOrderByCreatedDateDesc(user, ACTIVE).stream()
                    .map(record -> new RecordListResponse.RecordDto(
                            record.getRecordIdx(),
                            record.getRecordImage(),
                            record.getProperty().getLocation().getBuildingName(),
                            record.getProperty().getLocation().getAddress(),
                            record.getTotalRating())).toList();
            return new RecordListResponse(recordList);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 기록 상세 조회
    public RecordResponse getRecord(Long recordIdx) throws BaseException {
        try {
            Records record = recordRepository.findById(recordIdx).orElseThrow(() -> new BaseException(INVALID_RECORD_IDX));

            return new RecordResponse(
                    record.getRecordIdx(),
                    record.getRecordImage(),
                    record.getProperty().getLocation().getBuildingName(),
                    record.getProperty().getLocation().getAddress(),
                    record.getTotalRating(),
                    record.getWaterPressure(), record.getSoundProofing(), record.getDayLighting(),
                    record.getHeating(), record.getSecurity(), record.getFacility(),
                    record.getMemo());
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 기록 등록
    @Transactional(rollbackFor = Exception.class)
    public void postRecord(MultipartFile image, RecordRequest recordRequest, Long uid) throws BaseException {
        try {
            Users user = userRepository.findByUserIdx(uid);
            Properties property = propertyRepository.findById(recordRequest.propertyIdx()).orElseThrow(() -> new BaseException(INVALID_PROPERTY_IDX));

            // upload image
            String fullPath = gcsService.uploadImage("record", image);
            String recordImageUrl = "https://storage.googleapis.com/"+bucketName+"/"+fullPath;

            Records newRecord = Records.builder()
                    .user(user)
                    .property(property)
                    .waterPressure(recordRequest.waterPressure())
                    .soundProofing(recordRequest.soundProofing())
                    .dayLighting(recordRequest.dayLighting())
                    .heating(recordRequest.heating())
                    .security(recordRequest.security())
                    .facility(recordRequest.facility())
                    .recordImage(recordImageUrl)
                    .memo(recordRequest.memo())
                    .totalRating(Double.valueOf(recordRequest.totalRating()))
                    .build();

            recordRepository.save(newRecord);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
