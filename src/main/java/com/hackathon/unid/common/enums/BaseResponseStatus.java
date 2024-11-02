package com.hackathon.unid.common.enums;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000: 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류
     */
    // users(2000-2099)

    // records(2100-2199)
    INVALID_RECORD_IDX(false, 2100, "유효하지 않은 record idx 입니다."),

    // properties(2200-2299)
    INVALID_PROPERTY_IDX(false, 2200, "유효하지 않은 property idx 입니다."),

    /**
     * 3000: Response 오류
     */
    // users(3000~3099)

    /**
     * 4000: DB, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패했습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
