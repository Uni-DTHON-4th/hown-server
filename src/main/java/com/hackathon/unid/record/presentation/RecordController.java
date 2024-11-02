package com.hackathon.unid.record.presentation;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.common.base.BaseResponse;
import com.hackathon.unid.record.application.RecordService;
import com.hackathon.unid.record.domain.dto.RecordListResponse;
import com.hackathon.unid.record.domain.dto.RecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.hackathon.unid.common.constants.RequestURI.record;

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
}
