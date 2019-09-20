package com.myproject.milkproducts.service.validation.cheeseValidation;

import com.myproject.milkproducts.dto.CheeseDto;
import com.myproject.milkproducts.service.validation.ValidationException;

public interface CheeseValidationeRule {


    void validate(CheeseDto cheeseDto);

    default void checkNotNull(CheeseDto cheeseDto) {
        if (cheeseDto == null) {
            throw new ValidationException("Cheese must be not null!!!");
        }
    }
}
