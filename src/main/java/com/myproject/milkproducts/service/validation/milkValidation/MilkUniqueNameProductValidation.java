package com.myproject.milkproducts.service.validation.milkValidation;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.repository.milkProductRepository.MilkProductRepository;
import com.myproject.milkproducts.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MilkUniqueNameProductValidation implements MilkProductValidationRule {


    private MilkProductRepository milkProductRepository;

    @Autowired
    public MilkUniqueNameProductValidation(MilkProductRepository milkProductRepository) {
        this.milkProductRepository = milkProductRepository;
    }

    @Override
    public void validate(MilkProductDto milkProductDto) {
        checkNotNull(milkProductDto);
        if (milkProductRepository.existsByName(milkProductDto.getName())) {
            throw new ValidationException("Product with this name is already exist");
        }
    }
}
