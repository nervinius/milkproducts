package com.myproject.milkproducts.service;

import com.myproject.milkproducts.domain.Yogurt;
import com.myproject.milkproducts.dto.YogurtDto;
import com.myproject.milkproducts.mapper.YogurtConverter;
import com.myproject.milkproducts.repository.yogurtRepository.YogurtRepository;
import com.myproject.milkproducts.service.validation.YogurtValidation.YogurtValidationService;
import com.myproject.milkproducts.service.validation.milkValidation.MilkProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class YogurtService {

    private YogurtValidationService yogurtValidationService;
    private MilkProductValidationService validationService;
    private YogurtConverter yogurtConverter;
    private YogurtRepository yogurtRepository;

    @Autowired
    public YogurtService(YogurtValidationService yogurtValidationService, MilkProductValidationService validationService, YogurtConverter yogurtConverter, YogurtRepository yogurtRepository) {
        this.yogurtValidationService = yogurtValidationService;
        this.validationService = validationService;
        this.yogurtConverter = yogurtConverter;
        this.yogurtRepository = yogurtRepository;
    }

    @Transactional
    public Long createYogurt(YogurtDto yogurtDto) {
        validationService.validate(yogurtDto);
        yogurtValidationService.validate(yogurtDto);
        Yogurt cheese = yogurtConverter.convert(yogurtDto);
        yogurtRepository.save(cheese);
        return cheese.getId();
    }

    public List<YogurtDto> showAllYogurts() {
        return yogurtRepository.findAll().stream()
                .map(cheese -> yogurtConverter.convert(cheese))
                .collect(Collectors.toList());
    }

    public void yogurtUpdate(YogurtDto cheeseDto) {
        Yogurt cheese = yogurtConverter.convert(cheeseDto);
        yogurtRepository.save(cheese);

    }

    @Transactional
    public void deleteYogurtById(Long id) {
        yogurtRepository.findById(id)
                .ifPresent(yogurtRepository::delete);
    }

    public YogurtDto findYogurtById(Long id) {
        return yogurtRepository.findById(id)
                .map(yogurtConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Yogurt not found , id : " + id));
    }

    public YogurtDto findYogurtByName(String name) {
        return yogurtRepository.findYogurtByName(name)
                .map(yogurtConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Yogurt not found , name : " + name));
    }
}
