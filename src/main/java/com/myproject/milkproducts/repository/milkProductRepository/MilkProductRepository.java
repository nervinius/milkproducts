package com.myproject.milkproducts.repository.milkProductRepository;

import com.myproject.milkproducts.domain.MilkProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MilkProductRepository extends JpaRepository<MilkProduct, Long> {

    Optional<MilkProduct> findMilkProductByName(String name);

    boolean existsByName(String name);
}
