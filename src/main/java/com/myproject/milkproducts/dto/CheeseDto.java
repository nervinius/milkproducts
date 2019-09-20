package com.myproject.milkproducts.dto;

import com.myproject.milkproducts.domain.CheeseType;

import java.util.Objects;


public class CheeseDto extends MilkProductDto {

    private CheeseType cheeseType;

    public CheeseDto(CheeseType cheeseType) {
        this.cheeseType = cheeseType;
    }

    public CheeseDto() {
    }

    public CheeseType getCheeseType() {
        return cheeseType;
    }

    public void setCheeseType(CheeseType cheeseType) {
        this.cheeseType = cheeseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CheeseDto cheeseDto = (CheeseDto) o;
        return cheeseType == cheeseDto.cheeseType;
    }

    @Override
    public String toString() {
        return "CheeseDto{" +
                "cheeseType=" + cheeseType +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cheeseType);
    }

    public interface Update {
        // empty interface
    }

    public interface Create {
        // empty interface
    }
}
