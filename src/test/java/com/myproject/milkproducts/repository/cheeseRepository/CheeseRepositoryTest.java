package com.myproject.milkproducts.repository.cheeseRepository;

import com.myproject.milkproducts.domain.Cheese;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CheeseRepositoryTest {

    @Autowired
    private CheeseRepository victim;

    @Test
    public void shouldCreateProduct() {
        Cheese cheese = new Cheese();
        cheese.setName("TEST_NAME");
        cheese.setPrice(BigDecimal.TEN);
        cheese.setDescription("TEST_DESCRIPTION");

        Long result = victim.save(cheese).getId();

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindProductByName() {
        Cheese cheese = new Cheese();
        cheese.setName("TEST_NAME");
        cheese.setPrice(BigDecimal.TEN);
        cheese.setDescription("TEST_DESCRIPTION");


        Long id = victim.save(cheese).getId();

        Optional<Cheese> result = victim.findCheeseByName("TEST_NAME");

        assertThat(result).hasValue(expectedProduct(id));
    }

    @Test
    public void shouldFindProductById() {
        Cheese cheese = new Cheese();
        cheese.setName("TEST_NAME");
        cheese.setPrice(BigDecimal.TEN);
        cheese.setDescription("TEST_DESCRIPTION");


        Long id = victim.save(cheese).getId();

        Optional<Cheese> result = victim.findById(id);

        assertThat(result).hasValue(expectedProduct(id));

    }

    @Test
    public void shouldExistByName() {
        Cheese cheese = new Cheese();
        cheese.setName("TEST_NAME");
        cheese.setPrice(BigDecimal.TEN);
        cheese.setDescription("TEST_DESCRIPTION");


        victim.save(cheese);

        boolean result = victim.existsByName("TEST_NAME");

        assertThat(result).isTrue();
    }

    @Test
    public void shouldShowAllProducts() {
        Cheese cheese = new Cheese();
        cheese.setName("TEST_NAME");
        cheese.setPrice(BigDecimal.TEN);
        cheese.setDescription("TEST_DESCRIPTION");

        victim.save(cheese);

        List<Cheese> result = victim.findAll();

        assertThat(result).isNotNull();
    }

    public Cheese expectedProduct(Long id) {
        Cheese cheese = new Cheese();
        cheese.setName("TEST_NAME");
        cheese.setId(1L);
        cheese.setPrice(BigDecimal.TEN);
        cheese.setDescription("TEST_DESCRIPTION");

        return cheese;
    }

}