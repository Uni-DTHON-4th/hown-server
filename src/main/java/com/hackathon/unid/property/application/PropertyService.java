package com.hackathon.unid.property.application;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.property.domain.dto.PropertyListResponse;
import com.hackathon.unid.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hackathon.unid.common.constants.Constant.ACTIVE;
import static com.hackathon.unid.common.enums.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

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
}
