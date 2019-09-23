package com.myproject.milkproducts.service.validation.cheeseValidation;

import com.myproject.milkproducts.domain.CheeseType;
import com.myproject.milkproducts.dto.CheeseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CheeseValidationServiceTest {

    @Mock
    private CheeseTypeValidation cheeseTypeValidation;

    @Captor
    private ArgumentCaptor<CheeseDto> captor;

    private CheeseValidationService victim;

    private CheeseDto input = cheeseDto();

    @Before
    public void setUp() {
        Set<CheeseValidationeRule> rules = new HashSet<>();
        rules.add(cheeseTypeValidation);
        victim = new CheeseValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(input);


        Mockito.verify(cheeseTypeValidation).validate(captor.capture());

        List<CheeseDto> resultList = captor.getAllValues();
        resultList.forEach(cheese -> assertEquals(input, cheese));

    }

    private CheeseDto cheeseDto() {
        CheeseDto cheeseDto = new CheeseDto();
        cheeseDto.setCheeseType(CheeseType.Cheddar);
        cheeseDto.setId(1L);
        cheeseDto.setName("TEST");
        cheeseDto.setDescription("TEST");
        cheeseDto.setPrice(BigDecimal.valueOf(10));

        return cheeseDto;
    }

}