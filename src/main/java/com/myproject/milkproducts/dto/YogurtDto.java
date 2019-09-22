package com.myproject.milkproducts.dto;

import java.util.Objects;

public class YogurtDto extends MilkProductDto {

    private String flavor;

    public YogurtDto(String flavor) {
        this.flavor = flavor;
    }

    public YogurtDto() {
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YogurtDto yogurtDto = (YogurtDto) o;
        return Objects.equals(flavor, yogurtDto.flavor);
    }

    @Override
    public String toString() {
        return "YogurtDto{" +
                "flavor='" + flavor + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flavor);
    }

    public interface Update {
        // empty interface
    }

    public interface Create {
        // empty interface
    }
}
