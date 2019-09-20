package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class MilkProductDescriptionValidation implements MilkProductValidationRule {
    @Override
    public void validate(MilkProductDto milkProductDto) {
        checkNotNull(milkProductDto);
        if (milkProductDto.getDescription().length() < 1 || milkProductDto.getDescription().length() > 100) {
            throw new ValidationException("Description should be less then 100 symbols and more then 1 symbol");
        }
    }
}
