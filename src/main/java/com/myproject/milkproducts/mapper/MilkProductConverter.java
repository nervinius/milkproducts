package com.myproject.milkproducts.mapper;

import com.myproject.milkproducts.domain.MilkProduct;
import com.myproject.milkproducts.dto.MilkProductDto;
import org.springframework.stereotype.Component;

@Component
public class MilkProductConverter {

    public MilkProduct convert(MilkProductDto milkProductDto) {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setId(milkProductDto.getId());
        milkProduct.setName(milkProductDto.getName());
        milkProduct.setPrice(milkProductDto.getPrice());
        milkProduct.setDescription(milkProductDto.getDescription());
        return milkProduct;
    }

    public MilkProductDto convert(MilkProduct milkProduct) {
        MilkProductDto milkProductDto = new MilkProductDto();
        milkProductDto.setId(milkProduct.getId());
        milkProductDto.setName(milkProduct.getName());
        milkProductDto.setPrice(milkProduct.getPrice());
        milkProductDto.setDescription(milkProduct.getDescription());
        return milkProductDto;
    }
}
