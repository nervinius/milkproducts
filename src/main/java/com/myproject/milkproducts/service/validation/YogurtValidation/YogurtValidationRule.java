package com.myproject.milkproducts.service.validation.YogurtValidation;

import com.myproject.milkproducts.dto.YogurtDto;
import com.myproject.milkproducts.service.validation.ValidationException;

public interface YogurtValidationRule {

    void validate(YogurtDto yogurtDto);

    default void checkNotNull(YogurtDto yogurtDto) {
        if (yogurtDto == null) {
            throw new ValidationException("Yogurt must be not null!!!");
        }
    }
}
