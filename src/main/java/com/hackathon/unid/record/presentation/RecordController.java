package com.hackathon.unid.record.presentation;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.common.base.BaseResponse;
import com.hackathon.unid.record.application.RecordService;
import com.hackathon.unid.record.domain.dto.RecordListResponse;
import com.hackathon.unid.record.domain.dto.RecordRequest;
import com.hackathon.unid.record.domain.dto.RecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.hackathon.unid.common.constants.RequestURI.record;
import static com.hackathon.unid.common.enums.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(record)
public class RecordController {

    private final RecordService recordService;

    @GetMapping("")
    public BaseResponse<RecordListResponse> getRecords(@RequestParam Long uid) {
        try {
            return new BaseResponse<>(recordService.getRecords(uid));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/{recordIdx}")
    public BaseResponse<RecordResponse> getRecord(@PathVariable Long recordIdx) {
        try {
            return new BaseResponse<>(recordService.getRecord(recordIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public BaseResponse<?> postRecord(@RequestPart(value = "image", required = false) MultipartFile image, @RequestPart(value = "recordRequest") RecordRequest recordRequest, Long uid) {
        try {
            recordService.postRecord(image, recordRequest, uid);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
