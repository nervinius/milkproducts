package com.myproject.milkproducts.repository.yogurtRepository;

import com.myproject.milkproducts.domain.Yogurt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YogurtRepository extends JpaRepository<Yogurt,Long> {

    Optional<Yogurt> findYogurtByName(String name);
}
