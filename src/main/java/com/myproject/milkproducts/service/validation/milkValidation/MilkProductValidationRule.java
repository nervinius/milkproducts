package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.validation.ValidationException;

public interface MilkProductValidationRule {

    void validate(MilkProductDto milkProductDto);

    default void checkNotNull(MilkProductDto milkProductDto) {
        if (milkProductDto == null) {
            throw new ValidationException("MilkProduct must be not null!!!");
        }
    }
}
