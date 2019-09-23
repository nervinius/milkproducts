package com.myproject.milkproducts.repository.milkProductRepository;

import com.myproject.milkproducts.config.AppConfig;
import com.myproject.milkproducts.domain.MilkProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
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
public class MilkProductRepositoryTest {

    @Autowired
    private MilkProductRepository victim;

    @Test
    public void shouldCreateProduct() {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setName("TEST_NAME");
        milkProduct.setPrice(BigDecimal.TEN);
        milkProduct.setDescription("TEST_DESCRIPTION");

        Long result = victim.save(milkProduct).getId();

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindProductByName() {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setName("TEST_NAME");
        milkProduct.setPrice(BigDecimal.TEN);
        milkProduct.setDescription("TEST_DESCRIPTION");


        Long id = victim.save(milkProduct).getId();

        Optional<MilkProduct> result = victim.findMilkProductByName("TEST_NAME");

        assertThat(result).hasValue(expectedProduct(id));
    }

    @Test
    public void shouldFindProductById() {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setName("TEST_NAME");
        milkProduct.setPrice(BigDecimal.TEN);
        milkProduct.setDescription("TEST_DESCRIPTION");


        Long id = victim.save(milkProduct).getId();

        Optional<MilkProduct> result = victim.findById(id);

        assertThat(result).hasValue(expectedProduct(id));

    }

    @Test
    public void shouldExistByName() {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setName("TEST_NAME");
        milkProduct.setPrice(BigDecimal.TEN);
        milkProduct.setDescription("TEST_DESCRIPTION");


        victim.save(milkProduct);

        boolean result = victim.existsByName("TEST_NAME");

        assertThat(result).isTrue();
    }

    @Test
    public void shouldShowAllProducts() {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setName("TEST_NAME");
        milkProduct.setPrice(BigDecimal.TEN);
        milkProduct.setDescription("TEST_DESCRIPTION");

        victim.save(milkProduct);

        List<MilkProduct> result = victim.findAll();

        assertThat(result).isNotNull();
    }

    public MilkProduct expectedProduct(Long id) {
        MilkProduct milkProduct = new MilkProduct();
        milkProduct.setName("TEST_NAME");
        milkProduct.setId(1L);
        milkProduct.setPrice(BigDecimal.TEN);
        milkProduct.setDescription("TEST_DESCRIPTION");

        return milkProduct;
    }

}