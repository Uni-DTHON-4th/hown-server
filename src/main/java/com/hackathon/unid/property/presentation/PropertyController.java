package com.hackathon.unid.property.presentation;

import com.hackathon.unid.common.base.BaseException;
import com.hackathon.unid.common.base.BaseResponse;
import com.hackathon.unid.property.application.PropertyService;
import com.hackathon.unid.property.domain.dto.PropertyListResponse;
import com.hackathon.unid.property.domain.dto.PropertyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hackathon.unid.common.constants.RequestURI.property;

@RestController
@RequiredArgsConstructor
@RequestMapping(property)
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("")
    public BaseResponse<PropertyListResponse> getProperties() {
        try {
            return new BaseResponse<>(propertyService.getPropertyList());
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("{propertyIdx}")
    public BaseResponse<PropertyResponse> getProperty(@PathVariable Long propertyIdx) {
        try {
            return new BaseResponse<>(propertyService.getProperty(propertyIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
