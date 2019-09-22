package com.myproject.milkproducts.service.validation.YogurtValidation;

import com.myproject.milkproducts.dto.YogurtDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class YogurtValidationService {

    private final Set<YogurtValidationRule> validationRules;

    public YogurtValidationService(Set<YogurtValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(YogurtDto yogurtDto) {
        validationRules.forEach(s -> s.validate(yogurtDto));
    }
}
