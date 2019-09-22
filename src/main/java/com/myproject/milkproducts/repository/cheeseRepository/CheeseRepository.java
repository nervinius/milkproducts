package com.myproject.milkproducts.repository.cheeseRepository;

import com.myproject.milkproducts.domain.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheeseRepository extends JpaRepository<Cheese, Long> {

    Optional<Cheese> findCheeseByName(String name);

    boolean existsByName(String name);
}
