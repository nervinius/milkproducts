package com.myproject.milkproducts.service.validation.cheeseValidation;

import com.myproject.milkproducts.dto.CheeseDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CheeseTypeValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CheeseTypeValidation victim = new CheeseTypeValidation();

    private CheeseDto input;

    @Test
    public void shouldThrowValidationException() {
        input = cheeseDto();
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Cheese type should be not null!");
        victim.validate(input);
    }

    private CheeseDto cheeseDto() {
        CheeseDto cheeseDto = new CheeseDto();
        cheeseDto.setCheeseType(null);
        return cheeseDto;
    }

}