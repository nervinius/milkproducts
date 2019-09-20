package com.myproject.milkproducts.service;

import com.myproject.milkproducts.domain.MilkProduct;
import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.mapper.MilkProductConverter;
import com.myproject.milkproducts.repository.milkProductRepository.MilkProductRepository;
import com.myproject.milkproducts.service.validation.milkValidation.MilkProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MilkProductService {

    private MilkProductRepository milkProductRepository;
    private MilkProductConverter milkProductConverter;
    private MilkProductValidationService validationService;

    @Autowired
    public MilkProductService(MilkProductRepository milkProductRepository, MilkProductConverter milkProductConverter, MilkProductValidationService validationService) {
        this.milkProductRepository = milkProductRepository;
        this.milkProductConverter = milkProductConverter;
        this.validationService = validationService;
    }

    @Transactional
    public Long createMilkProduct(MilkProductDto milkProductDto) {
        validationService.validate(milkProductDto);
        MilkProduct milkProduct = milkProductConverter.convert(milkProductDto);
        milkProductRepository.save(milkProduct);
        return milkProduct.getId();
    }

    public List<MilkProductDto> showAllMilk() {
        return milkProductRepository.findAll().stream()
                .map(milk -> milkProductConverter.convert(milk))
                .collect(Collectors.toList());
    }

    public void milkProductUpdate(MilkProductDto milkProductDto) {
        MilkProduct milkProduct = milkProductConverter.convert(milkProductDto);

        milkProductRepository.save(milkProduct);

    }

    @Transactional
    public void deleteMilkProductById(Long id) {
        milkProductRepository.findById(id)
                .ifPresent(milkProductRepository::delete);
    }

    public MilkProductDto findMilkProductById(Long id) {
        return milkProductRepository.findById(id)
                .map(milkProductConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("MilkProduct not found , id : " + id));
    }

    public MilkProductDto findMilkProductByName(String name){
        return milkProductRepository.findMilkProductByName(name)
                .map(milkProductConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("MilkProduct not found , name : " + name));
    }
}
