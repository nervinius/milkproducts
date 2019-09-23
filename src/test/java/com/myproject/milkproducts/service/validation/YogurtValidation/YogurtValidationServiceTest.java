package com.myproject.milkproducts.service.validation.YogurtValidation;

import com.myproject.milkproducts.dto.YogurtDto;
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

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class YogurtValidationServiceTest {

    @Mock
    private YogurtFlavorValidation yogurtFlavorValidation;

    @Captor
    private ArgumentCaptor<YogurtDto> captor;

    private YogurtValidationService victim;

    private YogurtDto input = yogurtDto();

    @Before
    public void setUp() {
        Set<YogurtValidationRule> rules = new HashSet<>();
        rules.add(yogurtFlavorValidation);
        victim = new YogurtValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(input);


        Mockito.verify(yogurtFlavorValidation).validate(captor.capture());

        List<YogurtDto> resultList = captor.getAllValues();
        resultList.forEach(yogurt -> assertEquals(input, yogurt));

    }

    private YogurtDto yogurtDto() {
        YogurtDto yogurtDto = new YogurtDto();
        yogurtDto.setFlavor("TEST_FLAVOR");
        yogurtDto.setId(1L);
        yogurtDto.setName("TEST");
        yogurtDto.setDescription("TEST");
        yogurtDto.setPrice(BigDecimal.valueOf(10));

        return yogurtDto;
    }

}