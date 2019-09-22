package com.myproject.milkproducts.service;

import com.myproject.milkproducts.domain.Cheese;
import com.myproject.milkproducts.dto.CheeseDto;
import com.myproject.milkproducts.mapper.CheeseConverter;
import com.myproject.milkproducts.repository.cheeseRepository.CheeseRepository;
import com.myproject.milkproducts.service.validation.cheeseValidation.CheeseValidationService;
import com.myproject.milkproducts.service.validation.milkValidation.MilkProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CheeseService {

    private CheeseValidationService cheeseValidationService;
    private MilkProductValidationService validationService;
    private CheeseRepository cheeseRepository;
    private CheeseConverter cheeseConverter;

    public CheeseService(CheeseValidationService cheeseValidationService, MilkProductValidationService validationService, CheeseRepository cheeseRepository, CheeseConverter cheeseConverter) {
        this.cheeseValidationService = cheeseValidationService;
        this.validationService = validationService;
        this.cheeseRepository = cheeseRepository;
        this.cheeseConverter = cheeseConverter;
    }

    @Transactional
    public Long createCheese(CheeseDto cheeseDto) {
        validationService.validate(cheeseDto);
        cheeseValidationService.validate(cheeseDto);
        Cheese cheese = cheeseConverter.convert(cheeseDto);
        cheeseRepository.save(cheese);
        return cheese.getId();
    }

    public List<CheeseDto> showAllCheese() {
        return cheeseRepository.findAll().stream()
                .map(cheese -> cheeseConverter.convert(cheese))
                .collect(Collectors.toList());
    }

    public void cheeseUpdate(CheeseDto cheeseDto) {
        Cheese cheese = cheeseConverter.convert(cheeseDto);
        cheeseRepository.save(cheese);

    }

    @Transactional
    public void deleteCheeseById(Long id) {
        cheeseRepository.findById(id)
                .ifPresent(cheeseRepository::delete);
    }

    public CheeseDto findCheeseById(Long id) {
        return cheeseRepository.findById(id)
                .map(cheeseConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Cheese not found , id : " + id));
    }

    public CheeseDto findCheeseByName(String name) {
        return cheeseRepository.findCheeseByName(name)
                .map(cheeseConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Cheese not found , name : " + name));
    }
}

