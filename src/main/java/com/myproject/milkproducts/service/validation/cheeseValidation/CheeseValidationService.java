package com.myproject.milkproducts.service.validation.cheeseValidation;

import com.myproject.milkproducts.dto.CheeseDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CheeseValidationService {

    private final Set<CheeseValidationeRule> validationRules;

    public CheeseValidationService(Set<CheeseValidationeRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(CheeseDto cheeseDto) {
        validationRules.forEach(s -> s.validate(cheeseDto));
    }
}
