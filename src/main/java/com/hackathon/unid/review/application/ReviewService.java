package com.hackathon.unid.review.application;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.review.domain.dto.HomeResponse;
import com.hackathon.unid.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hackathon.unid.common.constants.Constant.ACTIVE;
import static com.hackathon.unid.common.enums.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 홈 조회 (최근 리뷰 5개 조회)
    public HomeResponse getHome() throws BaseException {
        try {
            List<HomeResponse.ReviewDto> reviewList = reviewRepository.findTop5ByStatusEqualsOrderByCreatedDateDesc(ACTIVE).stream()
                    .map(review -> new HomeResponse.ReviewDto(
                            review.getReviewIdx(),
                            review.getReviewImage(),
                            review.getTotalRating(),
                            review.getProperty().getLocation().getBuildingName(),
                            review.getProperty().getLocation().getAddress())).toList();
            return new HomeResponse(reviewList);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
