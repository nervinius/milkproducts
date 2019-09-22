package com.myproject.milkproducts.mapper;

import com.myproject.milkproducts.domain.Cheese;
import com.myproject.milkproducts.dto.CheeseDto;
import org.springframework.stereotype.Component;

@Component
public class CheeseConverter {

    public Cheese convert(CheeseDto cheeseDto) {
        Cheese cheese = new Cheese();
        cheese.setId(cheeseDto.getId());
        cheese.setName(cheeseDto.getName());
        cheese.setDescription(cheeseDto.getDescription());
        cheese.setPrice(cheeseDto.getPrice());
        cheese.setCheeseType(cheeseDto.getCheeseType());
        return cheese;
    }

    public CheeseDto convert(Cheese cheese) {
        CheeseDto cheeseDto = new CheeseDto();
        cheeseDto.setId(cheese.getId());
        cheeseDto.setName(cheese.getName());
        cheeseDto.setDescription(cheese.getName());
        cheeseDto.setPrice(cheese.getPrice());
        cheeseDto.setCheeseType(cheese.getCheeseType());
        return cheeseDto;
    }
}
