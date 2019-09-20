package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class MilkProductPriceValidation implements MilkProductValidationRule {
    @Override
    public void validate(MilkProductDto milkProductDto) {
        checkNotNull(milkProductDto);
        if (milkProductDto.getPrice().compareTo(new BigDecimal(BigInteger.ZERO)) == 0 || milkProductDto.getPrice().compareTo(new BigDecimal(BigInteger.ZERO)) < 0) {
            throw new ValidationException("MilkProduct price must be bigger then 0!");
        }
    }
}
