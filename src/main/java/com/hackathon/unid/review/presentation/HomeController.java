package com.hackathon.unid.review.presentation;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.common.base.BaseResponse;
import com.hackathon.unid.review.application.ReviewService;
import com.hackathon.unid.review.domain.dto.HomeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hackathon.unid.common.constants.RequestURI.home;

@RestController
@RequiredArgsConstructor
@RequestMapping(home)
public class HomeController {

    private final ReviewService reviewService;

    @GetMapping("")
    public BaseResponse<HomeResponse> getHome() {
        try {
            return new BaseResponse<>(reviewService.getHome());
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
