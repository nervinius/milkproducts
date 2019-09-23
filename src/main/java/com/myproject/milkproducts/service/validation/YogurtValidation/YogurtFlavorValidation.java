package com.myproject.milkproducts.service.validation.YogurtValidation;

import com.myproject.milkproducts.dto.YogurtDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class YogurtFlavorValidation implements YogurtValidationRule {

    @Override
    public void validate(YogurtDto yogurtDto) {
        checkNotNull(yogurtDto);
        if (yogurtDto.getFlavor() == null) {
            throw new ValidationException("Yogurt flavor must be not null!");
        } else if (yogurtDto.getFlavor().length() <= 1 || yogurtDto.getFlavor().length() >= 20) {
            throw new ValidationException(("Yogurt flavor length must be longer than 1 symbols and shorter than 20 symbols!"));
        }
    }
}
