package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class MilkProductNameValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MilkProductNameValidation victim = new MilkProductNameValidation();

    private MilkProductDto input;

    @Test
    public void shouldThrowValidationException() {
        input = milkProductDto(null);
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("MilkProduct name must be not null!");
        victim.validate(input);
    }

    private MilkProductDto milkProductDto(String name) {
        MilkProductDto milkProductDto = new MilkProductDto();
        milkProductDto.setName(name);
        return milkProductDto;
    }

}