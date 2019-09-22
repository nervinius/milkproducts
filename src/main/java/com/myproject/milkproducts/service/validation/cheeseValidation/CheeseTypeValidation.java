package com.myproject.milkproducts.service.validation.cheeseValidation;

import com.myproject.milkproducts.dto.CheeseDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CheeseTypeValidation implements CheeseValidationeRule {

    @Override
    public void validate(CheeseDto cheeseDto) {
        checkNotNull(cheeseDto);
        if (cheeseDto.getCheeseType() == null) {
            throw new ValidationException("Cheese type should be not null!");
        }
    }
}
