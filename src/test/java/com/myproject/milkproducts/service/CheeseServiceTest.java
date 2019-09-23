package com.myproject.milkproducts.service;

import com.myproject.milkproducts.domain.Cheese;
import com.myproject.milkproducts.dto.CheeseDto;
import com.myproject.milkproducts.mapper.CheeseConverter;
import com.myproject.milkproducts.repository.cheeseRepository.CheeseRepository;
import com.myproject.milkproducts.service.validation.cheeseValidation.CheeseValidationService;
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
public class CheeseServiceTest {
    @Mock
    private CheeseRepository cheeseRepository;

    @Mock
    private CheeseValidationService validationService;

    @Mock
    private CheeseConverter cheeseConverter;

    @Mock
    private MilkProductValidationService milkProductValidationService;

    @Captor
    private ArgumentCaptor<CheeseDto> productCaptor;

    @InjectMocks
    private CheeseService victim;

    @Before
    public void setUp() throws Exception {
        victim = new CheeseService(validationService, milkProductValidationService, cheeseRepository, cheeseConverter);
    }


    @Test
    public void shouldCreateProduct() {
        Cheese cheese = new Cheese();
        CheeseDto cheeseDto = new CheeseDto();
        when(cheeseConverter.convert(cheeseDto)).thenReturn(cheese);
        when(cheeseRepository.save(cheese)).thenReturn(cheese);

        Long result = victim.createCheese(cheeseDto);

        verify(milkProductValidationService).validate(productCaptor.capture());
        CheeseDto captorResult = productCaptor.getValue();

        assertThat(captorResult).isEqualTo(cheeseDto);
        assertThat(cheese.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductByName() {
        when(cheeseRepository.findCheeseByName("TEST_NAME")).thenReturn(Optional.ofNullable(cheese()));
        when(cheeseConverter.convert(cheese())).thenReturn(cheeseDto());

        CheeseDto result = victim.findCheeseByName("TEST_NAME");

        assertThat(result).isEqualTo(cheeseDto());
    }

    @Test
    public void shouldFindProductById() {

        when(cheeseRepository.findById(1001L)).thenReturn(Optional.ofNullable(cheese()));
        when(cheeseConverter.convert(cheese())).thenReturn(cheeseDto());

        CheeseDto result = victim.findCheeseById(1001L);

        assertThat(result).isEqualTo(cheeseDto());
    }


    @Test
    public void shouldThrowExceptionMilkProductNotFound() {
        when(cheeseRepository.findById(Matchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findCheeseById(1001L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cheese not found , id : 1001");
    }

    private CheeseDto cheeseDto() {
        CheeseDto cheeseDto = new CheeseDto();
        cheeseDto.setName("TEST_NAME");
        cheeseDto.setDescription("TEST_DESCRIPTION");
        cheeseDto.setId(1001L);
        return cheeseDto;
    }

    private Cheese cheese() {
        Cheese cheese = new Cheese();
        cheese.setName("TEST_NAME");
        cheese.setDescription("TEST_DESCRIPTION");
        cheese.setId(1001L);
        return cheese;
    }

}