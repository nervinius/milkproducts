package com.myproject.milkproducts.repository.yogurtRepository;


import com.myproject.milkproducts.domain.Yogurt;
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
public class YogurtRepositoryTest {

    @Autowired
    private YogurtRepository victim;

    @Test
    public void shouldCreateProduct() {
        Yogurt yogurt = new Yogurt();
        yogurt.setName("TEST_NAME");
        yogurt.setPrice(BigDecimal.TEN);
        yogurt.setDescription("TEST_DESCRIPTION");

        Long result = victim.save(yogurt).getId();

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindProductByName() {
        Yogurt yogurt = new Yogurt();
        yogurt.setName("TEST_NAME");
        yogurt.setPrice(BigDecimal.TEN);
        yogurt.setDescription("TEST_DESCRIPTION");


        Long id = victim.save(yogurt).getId();

        Optional<Yogurt> result = victim.findYogurtByName("TEST_NAME");

        assertThat(result).hasValue(expectedProduct(id));
    }

    @Test
    public void shouldFindProductById() {
        Yogurt yogurt = new Yogurt();
        yogurt.setName("TEST_NAME");
        yogurt.setPrice(BigDecimal.TEN);
        yogurt.setDescription("TEST_DESCRIPTION");


        Long id = victim.save(yogurt).getId();

        Optional<Yogurt> result = victim.findById(id);

        assertThat(result).hasValue(expectedProduct(id));

    }

    @Test
    public void shouldExistByName() {
        Yogurt yogurt = new Yogurt();
        yogurt.setName("TEST_NAME");
        yogurt.setPrice(BigDecimal.TEN);
        yogurt.setDescription("TEST_DESCRIPTION");


        victim.save(yogurt);

        boolean result = victim.existByName("TEST_NAME");

        assertThat(result).isTrue();
    }

    @Test
    public void shouldShowAllProducts() {
        Yogurt yogurt = new Yogurt();
        yogurt.setName("TEST_NAME");
        yogurt.setPrice(BigDecimal.TEN);
        yogurt.setDescription("TEST_DESCRIPTION");

        victim.save(yogurt);

        List<Yogurt> result = victim.findAll();

        assertThat(result).isNotNull();
    }

    public Yogurt expectedProduct(Long id) {
        Yogurt yogurt = new Yogurt();
        yogurt.setName("TEST_NAME");
        yogurt.setId(1L);
        yogurt.setPrice(BigDecimal.TEN);
        yogurt.setDescription("TEST_DESCRIPTION");

        return yogurt;
    }

}