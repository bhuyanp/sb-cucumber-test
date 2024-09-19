package com.example.sbtest.product;

import com.example.sbtest.Product;
import com.example.sbtest.ProductService;
import feign.FeignException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Slf4j
@RequiredArgsConstructor
public class ProductStepDefinitions {
    public static final String PRODUCT_TAG = "@Product";
    private final ProductFeignClient productFeignClient;
    private final ProductService productService;
    private final ProductContext context;



    private static List<Product> testProducts = List.of(
            new Product(-1, "TEST PRODUCT 1", 11.25),
            new Product(-1, "TEST PRODUCT 2", 12.25),
            new Product(-1, "TEST PRODUCT 3", 13.25),
            new Product(-1, "TEST PRODUCT 4", 14.25)
    );




    @Before(PRODUCT_TAG)
    public void beforeEachProductScenario(Scenario scenario) {
        log.info("Before: {}", scenario.getName());
        testProducts = testProducts.stream().map(productService::add).toList();
        log.info("Test Products added: {}", testProducts);
        log.info("Product Context: {}", context);
    }

    @Given("product with id {long} exists")
    public void productWithIdExists(long id) {
        log.info("Product id set to : {}", id);
        productService.getById(id);
        context.setProductId(id);
    }

    @When("user fetches the product")
    public void userFetchesTheProduct() {
        try {
            ResponseEntity<Product> productResponseEntity = productFeignClient.getById(context.getProductId());
            context.setHttpStatusCode(productResponseEntity.getStatusCode().value());
            if (productResponseEntity.getStatusCode().is2xxSuccessful()) {
                Product product = productResponseEntity.getBody();
                log.info("Product getting added to the context: {}", product);
                context.setProduct(product);
            }
        } catch (FeignException fe) {
            log.info("Error inside userFetchesTheProduct.{}", fe.getMessage());
            context.setHttpStatusCode(fe.status());
            context.setErrorMessage(fe.getMessage());
        }
    }

    @Then("product has id {long}")
    public void productHasId(long id) {
        assertThat(id).isEqualTo(context.getProduct().getId());
    }

    @And("response status is {int}")
    public void responseStatusIs(int expectedStatusCode) {
        assertThat(context.getHttpStatusCode()).isEqualTo(expectedStatusCode);
    }

    @Given("product with id {long} does not exist")
    public void productWithIdDoesNotExist(long id) {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> productService.getById(id));
        context.setProductId(id);
    }

    @After(PRODUCT_TAG)
    public void afterEachProductScenario(Scenario scenario) {
        log.info("After: {}", scenario.getName());
        testProducts.forEach(product -> {
            log.info("Removing test user: {}", product);
            productService.remove(product);
        });
    }

    @When("user adds new product with title {string} and price {double}")
    public void userAddsNewProductWithTitleTestProductNewAndPrice(String title, double price) {
        try {
            ResponseEntity<Product> productResponseEntity = productFeignClient
                    .addProduct(Product.builder()
                            .title(title)
                            .price(price)
                            .build());
            context.setHttpStatusCode(productResponseEntity.getStatusCode().value());
            if (productResponseEntity.getStatusCode().is2xxSuccessful()) {
                Product product = productResponseEntity.getBody();
                log.info("Product getting added to the context: {}", product);
                context.setProduct(product);
            }
        } catch (FeignException fe) {
            log.info("Error inside userFetchesTheProduct.{}", fe.getMessage());
            context.setHttpStatusCode(fe.status());
            context.setErrorMessage(fe.getMessage());
        }

    }

    @And("product title is {string}")
    public void productTitleIs(String expectedTitle) {
        assertThat(context.getProduct().getTitle()).isEqualTo(expectedTitle);
    }

    @And("product price is {double}")
    public void productPriceIs(double expectedPrice) {
        assertThat(context.getProduct().getPrice()).isEqualTo(expectedPrice);
    }
}
