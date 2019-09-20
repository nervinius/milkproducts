package com.myproject.milkproducts.domain;



import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cheese")
@PrimaryKeyJoinColumn(name = "cheese_id",referencedColumnName = "milkProduct_id")
public class Cheese extends MilkProduct {

    @Enumerated(EnumType.STRING)
    @Column(name = "cheese_type")
    private CheeseType cheeseType;

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
        Cheese cheese = (Cheese) o;
        return cheeseType == cheese.cheeseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cheeseType);
    }

    @Override
    public String toString() {
        return "Cheese{" +
                "cheeseType=" + cheeseType +
                '}';
    }
}
