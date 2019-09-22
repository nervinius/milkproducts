package com.myproject.milkproducts.mapper;

import com.myproject.milkproducts.domain.Yogurt;
import com.myproject.milkproducts.dto.YogurtDto;
import org.springframework.stereotype.Component;


@Component
public class YogurtConverter {

    public Yogurt convert(YogurtDto yogurtDto){
        Yogurt yogurt = new Yogurt();
        yogurt.setId(yogurtDto.getId());
        yogurt.setName(yogurtDto.getName());
        yogurt.setDescription(yogurtDto.getDescription());
        yogurt.setPrice(yogurtDto.getPrice());
        yogurt.setFlavor(yogurtDto.getFlavor());
        return yogurt;
    }
    public YogurtDto convert(Yogurt yogurt){
        YogurtDto yogurtDto = new YogurtDto();
        yogurtDto.setId(yogurt.getId());
        yogurtDto.setName(yogurt.getName());
        yogurtDto.setDescription(yogurtDto.getDescription());
        yogurtDto.setPrice(yogurtDto.getPrice());
        yogurtDto.setFlavor(yogurtDto.getFlavor());
        return yogurtDto;
    }
}
