package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MilkProductPriceValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MilkProductPriceValidation victim = new MilkProductPriceValidation();

    private MilkProductDto input;

    @Test
    public void shouldThrowValidationException() {
        input = milkProductDto(new BigDecimal("-5"));
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("MilkProduct price must be bigger then 0!");
        victim.validate(input);
    }

    private MilkProductDto milkProductDto(BigDecimal price) {
        MilkProductDto milkProductDto = new MilkProductDto();
        milkProductDto.setPrice(price);
        return milkProductDto;
    }

}