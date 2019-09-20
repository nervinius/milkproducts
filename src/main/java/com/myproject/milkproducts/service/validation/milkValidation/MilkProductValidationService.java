package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MilkProductValidationService {

    private final Set<MilkProductValidationRule> validationRules;

    public MilkProductValidationService(Set<MilkProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(MilkProductDto milkProductDto){
        validationRules.forEach(s->s.validate(milkProductDto));
    }
}
