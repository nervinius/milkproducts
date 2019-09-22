package com.myproject.milkproducts.controller;

import com.myproject.milkproducts.domain.Yogurt;
import com.myproject.milkproducts.dto.YogurtDto;
import com.myproject.milkproducts.service.YogurtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/yogurt")
public class YogurtController {

    @Autowired
    private YogurtService yogurtService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated({YogurtDto.Create.class}) @RequestBody YogurtDto yogurtDto,
                                       UriComponentsBuilder builder) {
        Long id = yogurtService.createYogurt(yogurtDto);
        return ResponseEntity.created(builder.path("/yogurt/{id}").buildAndExpand(id).toUri()).build();
    }

    @GetMapping
    public List<YogurtDto> getAllMilk() {
        return yogurtService.showAllYogurts();
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @Validated({YogurtDto.Update.class}) @RequestBody YogurtDto yogurtDto) {
        yogurtService.yogurtUpdate(yogurtDto);
    }

    @GetMapping(params = "name")
    public YogurtDto findProductByName(@RequestParam("name") String name) {
        return yogurtService.findYogurtByName(name);
    }

    @GetMapping("{id}")
    public YogurtDto findProductById(@PathVariable("id") Long id) {
        return yogurtService.findYogurtById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        yogurtService.deleteYogurtById(id);
    }
}
