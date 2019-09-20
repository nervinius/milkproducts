package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class MilkProductNameValidation implements MilkProductValidationRule {

    @Override
    public void validate(MilkProductDto milkProductDto) {
        checkNotNull(milkProductDto);
        if (milkProductDto.getName() == null) {
            throw new ValidationException("MilkProduct name must be not null!");
        } else if (milkProductDto.getName().length() <= 1 || milkProductDto.getName().length() >= 20) {
            throw new ValidationException(("MilkProduct name length must be longer than 1 symbols and shorter than 20 symbols!"));
        }

    }


}
