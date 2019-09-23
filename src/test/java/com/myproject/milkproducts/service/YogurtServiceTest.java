package com.myproject.milkproducts.service;

import com.myproject.milkproducts.domain.Yogurt;
import com.myproject.milkproducts.dto.YogurtDto;
import com.myproject.milkproducts.mapper.YogurtConverter;
import com.myproject.milkproducts.repository.yogurtRepository.YogurtRepository;
import com.myproject.milkproducts.service.validation.YogurtValidation.YogurtValidationService;
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
public class YogurtServiceTest {

    @Mock
    private MilkProductValidationService milkProductValidationService;

    @Mock
    private YogurtValidationService yogurtValidationService;

    @Mock
    private YogurtConverter yogurtConverter;

    @Mock
    private YogurtRepository yogurtRepository;

    @Captor
    private ArgumentCaptor<YogurtDto> captor;

    @InjectMocks
    private YogurtService victim;

    @Before
    public void setUp() throws Exception {
        victim = new YogurtService(yogurtValidationService, milkProductValidationService, yogurtConverter, yogurtRepository);
    }


    @Test
    public void shouldCreateProduct() {
        Yogurt yogurt = new Yogurt();
        YogurtDto yogurtDto = new YogurtDto();
        when(yogurtConverter.convert(yogurtDto)).thenReturn(yogurt);
        when(yogurtRepository.save(yogurt)).thenReturn(yogurt);

        Long result = victim.createYogurt(yogurtDto);

        verify(milkProductValidationService).validate(captor.capture());
        YogurtDto captorResult = captor.getValue();

        assertThat(captorResult).isEqualTo(yogurtDto);
        assertThat(yogurt.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductByName() {
        when(yogurtRepository.findYogurtByName("TEST_NAME")).thenReturn(Optional.ofNullable(yogurt()));
        when(yogurtConverter.convert(yogurt())).thenReturn(yogurtDto());

        YogurtDto result = victim.findYogurtByName("TEST_NAME");

        assertThat(result).isEqualTo(yogurtDto());
    }

    @Test
    public void shouldFindProductById() {

        when(yogurtRepository.findById(1001L)).thenReturn(Optional.ofNullable(yogurt()));
        when(yogurtConverter.convert(yogurt())).thenReturn(yogurtDto());

        YogurtDto result = victim.findYogurtById(1001L);

        assertThat(result).isEqualTo(yogurtDto());
    }


    @Test
    public void shouldThrowExceptionMilkProductNotFound() {
        when(yogurtRepository.findById(Matchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findYogurtById(1001L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Yogurt not found , id : 1001");
    }

    private YogurtDto yogurtDto() {
        YogurtDto yogurtDto = new YogurtDto();
        yogurtDto.setName("TEST_NAME");
        yogurtDto.setDescription("TEST_DESCRIPTION");
        yogurtDto.setId(1001L);
        return yogurtDto;
    }

    private Yogurt yogurt() {
        Yogurt yogurt = new Yogurt();
        yogurt.setName("TEST_NAME");
        yogurt.setDescription("TEST_DESCRIPTION");
        yogurt.setId(1001L);
        return yogurt;
    }
}