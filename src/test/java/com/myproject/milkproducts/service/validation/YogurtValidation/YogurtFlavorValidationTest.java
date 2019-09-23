package com.myproject.milkproducts.service.validation.YogurtValidation;

import com.myproject.milkproducts.domain.Yogurt;
import com.myproject.milkproducts.dto.YogurtDto;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class YogurtFlavorValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private YogurtFlavorValidation victim = new YogurtFlavorValidation();

    private YogurtDto input;

    @Test
    public void shouldThrowValidationException() {
        input = yogurtDto("");
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Yogurt flavor length must be longer than 1 symbols and shorter than 20 symbols!");
        victim.validate(input);

    }

    private YogurtDto yogurtDto(String flavor) {
        YogurtDto yogurtDto = new YogurtDto();
        yogurtDto.setFlavor(flavor);
        return yogurtDto;
    }

}