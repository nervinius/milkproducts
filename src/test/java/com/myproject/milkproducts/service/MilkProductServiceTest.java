package com.myproject.milkproducts.service;

import com.myproject.milkproducts.domain.MilkProduct;
import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.mapper.MilkProductConverter;
import com.myproject.milkproducts.repository.milkProductRepository.MilkProductRepository;
import com.myproject.milkproducts.service.validation.milkValidation.MilkProductValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MilkProductServiceTest {

    @Mock
    private MilkProductRepository milkProductRepository;

    @Mock
    private MilkProductValidationService milkProductValidationService;

    @Mock
    private MilkProductConverter milkProductConverter;

    @Captor
    private ArgumentCaptor<MilkProductDto> productCaptor;

    @InjectMocks
    private MilkProductService victim;

    @Before
    public void setUp() throws Exception {
        victim = new MilkProductService(milkProductRepository, milkProductConverter, milkProductValidationService);
    }


    @Test
    public void shouldCreateProduct() {
        MilkProductDto milkProductDto = new MilkProductDto();
        MilkProduct milkProduct = new MilkProduct();
        when(milkProductConverter.convert(milkProductDto)).thenReturn(milkProduct);
        when(milkProductRepository.save(milkProduct)).thenReturn(milkProduct);

        Long result = victim.createMilkProduct(milkProductDto);

        verify(milkProductValidationService).validate(productCaptor.capture());
        MilkProductDto captorResult = productCaptor.getValue();

        assertThat(captorResult).isEqualTo(milkProductDto);
        assertThat(milkProduct.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductByName() {
        when(milkProductRepository.findMilkProductByName("TEST_NAME")).thenReturn(Optional.ofNullable(milkProduct()));
        when(milkProductConverter.convert(milkProduct())).thenReturn(milkProductDto());

        MilkProductDto result = victim.findMilkProductByName("TEST_NAME");

        assertThat(result).isEqualTo(milkProductDto());
    }

    @Test
    public void shouldFindProductById() {

        when(milkProductRepository.findById(1001L)).thenReturn(Optional.ofNullable(milkProduct()));
        when(milkProductConverter.convert(milkProduct())).thenReturn(milkProductDto());

        MilkProductDto result = victim.findMilkProductById(1001L);

        assertThat(result).isEqualTo(milkProductDto());
    }

    @Test
    public void shouldThrowExceptionMilkProductNotFound() {
        when(milkProductRepository.findById(Matchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findMilkProductById(1001L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("MilkProduct not found , id : 1001");
    }

    private MilkProductDto milkProductDto() {
        MilkProductDto milkProductDto = new MilkProductDto();
        milkProductDto.setName("TEST_NAME");
        milkProductDto.setDescription("TEST_DESCRIPTION");
        milkProductDto.setId(1001L);
        return milkProductDto;
    }

    private MilkProduct milkProduct() {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setName("TEST_NAME");
        milkProduct.setDescription("TEST_DESCRIPTION");
        milkProduct.setId(1001L);
        return milkProduct;
    }
}