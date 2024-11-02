package com.hackathon.unid.property.application;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.common.enums.AspectType;
import com.hackathon.unid.property.domain.dto.PropertyListResponse;
import com.hackathon.unid.property.domain.dto.PropertyResponse;
import com.hackathon.unid.property.domain.entity.Properties;
import com.hackathon.unid.property.repository.PropertyRepository;
import com.hackathon.unid.review.domain.entity.ReviewAspects;
import com.hackathon.unid.review.repository.ReviewAspectsRepository;
import com.hackathon.unid.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hackathon.unid.common.constants.Constant.ACTIVE;
import static com.hackathon.unid.common.enums.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewAspectsRepository reviewAspectsRepository;

    // 매물 목록 조회
    public PropertyListResponse getPropertyList() throws BaseException {
        try {
            List<PropertyListResponse.PropertyDto> propertyList = propertyRepository.findAllByStatusEqualsOrderByCreatedDateDesc(ACTIVE).stream()
                    .map(property -> new PropertyListResponse.PropertyDto(
                            property.getPropertyIdx(),
                            property.getLocation().getX(),
                            property.getLocation().getY())).toList();
            return new PropertyListResponse(propertyList);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 매물 상세 조회
    public PropertyResponse getProperty(Long propertyIdx) throws BaseException {
        try {
            Properties property = propertyRepository.findById(propertyIdx).orElseThrow(() -> new BaseException(INVALID_PROPERTY_IDX));

            Map<AspectType, Integer> ratingDto = new EnumMap<>(AspectType.class);
            for (AspectType aspect : AspectType.values()) {
                ratingDto.put(aspect, 0);
            }

            List<ReviewAspects> reviewAspects = reviewAspectsRepository.findAllByReview_PropertyAndReview_StatusEquals(property, ACTIVE);

            Map<AspectType, Double> averageRatings = reviewAspects.stream()
                    .collect(Collectors.groupingBy(
                            ReviewAspects::getAspectType,
                            Collectors.averagingInt(ReviewAspects::getAspectRating)));

            for (Map.Entry<AspectType, Double> entry : averageRatings.entrySet()) {
                ratingDto.put(entry.getKey(), (int) Math.round(entry.getValue()));
            }

            List<PropertyResponse.PropertyReviewDto> reviewList = reviewRepository.findAllByPropertyAndStatusEquals(property, ACTIVE).stream()
                    .map(review -> new PropertyResponse.PropertyReviewDto(
                            review.getUser().getNickname(),
                            review.getTotalRating(),
                            review.getPros(),
                            review.getCons(),
                            review.getReviewImage())).toList();

            return new PropertyResponse(
                    property.getLocation().getBuildingName(),
                    property.getLocation().getAddress(),
                    property.getPropertyImage(),
                    ratingDto,
                    reviewList);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
