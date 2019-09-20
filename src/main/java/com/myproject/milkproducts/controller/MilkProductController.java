package com.myproject.milkproducts.controller;

import com.myproject.milkproducts.dto.MilkProductDto;
import com.myproject.milkproducts.service.MilkProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/milkProduct")
public class MilkProductController {

    @Autowired
    private MilkProductService milkProductService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated({MilkProductDto.Create.class})@RequestBody MilkProductDto milkProductDto,
                                       UriComponentsBuilder builder){
        Long id = milkProductService.createMilkProduct(milkProductDto);
        return ResponseEntity.created(builder.path("/milk/{id}").buildAndExpand(id).toUri()).build();
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id,@Validated({MilkProductDto.Update.class})@RequestBody MilkProductDto milkProductDto){
        milkProductService.milkProductUpdate(milkProductDto);
    }

    @GetMapping
    public List<MilkProductDto> getAllMilkProducts(){
        return milkProductService.showAllMilk();
    }

    @GetMapping(params = "name")
    public MilkProductDto findProductByName(@RequestParam("name") String name){
        return milkProductService.findMilkProductByName(name);
    }
    @GetMapping("{id}")
    public MilkProductDto findProductById(@PathVariable("id") Long id){
        return milkProductService.findMilkProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id")Long id){
        milkProductService.deleteMilkProductById(id);
    }
}
