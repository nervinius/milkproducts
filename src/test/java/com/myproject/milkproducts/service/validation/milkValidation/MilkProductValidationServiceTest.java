package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MilkProductValidationServiceTest {

    @Mock
    private MilkProductDescriptionValidation milkProductDescriptionValidation;
    @Mock
    private MilkProductNameValidation milkProductNameValidation;
    @Mock
    private MilkProductPriceValidation milkProductPriceValidation;
    @Mock
    private MilkProductUniqueNameValidation milkProductUniqueNameValidation;

    @Captor
    private ArgumentCaptor<MilkProductDto> captor;

    private MilkProductValidationService victim;

    private MilkProductDto input = milkProductDto();

    @Before
    public void setUp() {
        Set<MilkProductValidationRule> rules = new HashSet<>();
        rules.add(milkProductDescriptionValidation);
        rules.add(milkProductNameValidation);
        rules.add(milkProductPriceValidation);
        rules.add(milkProductUniqueNameValidation);

        victim = new MilkProductValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(input);

        verify(milkProductDescriptionValidation).validate(captor.capture());
        verify(milkProductNameValidation).validate(captor.capture());
        verify(milkProductPriceValidation).validate(captor.capture());
        verify(milkProductUniqueNameValidation).validate(captor.capture());

        List<MilkProductDto> resultList = captor.getAllValues();
        resultList.forEach(milkProduct -> assertEquals(input, milkProduct));
    }

    private MilkProductDto milkProductDto() {
        MilkProductDto milkProductDto = new MilkProductDto();
        milkProductDto.setName("TEST_NAME");
        milkProductDto.setDescription("TEST_DESCRIPTION");
        milkProductDto.setPrice(BigDecimal.valueOf(10));
        milkProductDto.setId(1L);
        return milkProductDto;
    }

}