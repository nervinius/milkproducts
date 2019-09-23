package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class MilkProductDescriptionValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MilkProductDescriptionValidation victim = new MilkProductDescriptionValidation();

    private MilkProductDto input;

    @Test
    public void shouldThrowValidationException() {
        input = productDto("");
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Description should be less then 100 symbols and more then 1 symbol");
        victim.validate(input);

    }

    private MilkProductDto productDto(String description) {
        MilkProductDto milkProductDto = new MilkProductDto();
        milkProductDto.setDescription(description);
        return milkProductDto;
    }

}