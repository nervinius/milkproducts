package com.myproject.milkproducts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "yogurt")
@PrimaryKeyJoinColumn(name = "yogurt_id",referencedColumnName = "milkProduct_id")
public class Yogurt extends MilkProduct {

    @Column(name = "flavor")
    private String flavor;

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
        Yogurt yogurt = (Yogurt) o;
        return Objects.equals(flavor, yogurt.flavor);
    }

    @Override
    public String toString() {
        return "Yogurt{" +
                "flavor='" + flavor + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flavor);
    }
}
