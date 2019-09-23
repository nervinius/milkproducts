package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.repository.milkProductRepository.MilkProductRepository;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MilkProductUniqueNameValidationTest {

    @Mock
    private MilkProductRepository productRepository;

    @Spy
    @InjectMocks
    private MilkProductUniqueNameValidation victim;

    private MilkProductDto milkProductDto = milkProductDto();

    @Test
    public void shouldThrowException() {
        when(productRepository.existsByName(milkProductDto.getName()))
                .thenReturn(true);

        assertThatThrownBy(() -> victim.validate(milkProductDto()))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Product with this name is already exist");

        verify(victim).checkNotNull(milkProductDto);

    }

    @Test
    public void ShouldValidateSuccess() {
        when(productRepository.existsByName(milkProductDto.getName()))
                .thenReturn(false);
        victim.validate(milkProductDto);

        verify(victim).checkNotNull(milkProductDto);
    }

    private MilkProductDto milkProductDto() {
        MilkProductDto milkProductDto = new MilkProductDto();
        milkProductDto.setId(123L);
        milkProductDto.setName("TEST_NAME");
        milkProductDto.setDescription("TEST_DESCRIPTION");
        milkProductDto.setPrice(new BigDecimal("10"));

        return milkProductDto;
    }

}