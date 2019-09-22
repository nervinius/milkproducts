package com.myproject.milkproducts.controller;

import com.myproject.milkproducts.dto.CheeseDto;
import com.myproject.milkproducts.service.CheeseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/cheese")
public class CheeseController {

    @Autowired
    private CheeseService cheeseService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated({CheeseDto.Create.class}) @RequestBody CheeseDto cheeseDto,
                                       UriComponentsBuilder builder) {
        Long id = cheeseService.createCheese(cheeseDto);
        return ResponseEntity.created(builder.path("/cheese/{id}").buildAndExpand(id).toUri()).build();
    }

    @GetMapping
    public List<CheeseDto> getAllMilk() {
        return cheeseService.showAllCheese();
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @Validated({CheeseDto.Update.class}) @RequestBody CheeseDto cheeseDto) {
        cheeseService.cheeseUpdate(cheeseDto);
    }

    @GetMapping(params = "name")
    public CheeseDto findProductByName(@RequestParam("name") String name) {
        return cheeseService.findCheeseByName(name);
    }

    @GetMapping("{id}")
    public CheeseDto findProductById(@PathVariable("id") Long id) {
        return cheeseService.findCheeseById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        cheeseService.deleteCheeseById(id);
    }
}
