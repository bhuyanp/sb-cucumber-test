package com.example.sbtest.cucumber;

import com.example.sbtest.Product;
import com.example.sbtest.ProductService;
import feign.FeignException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Slf4j
@RequiredArgsConstructor
public class ProductStepDefinitions {
    private final ProductFeignClient productFeignClient;
    private final ProductService productService;

    private long productId;
    private Product product;
    private int httpStatusCode;



    @Given("product with id {long} exists")
    public void productWithIdExists(long id) {
        log.info("Product id set to : {}",id);
        productService.getById(id);
        productId = id;
    }

    @When("user fetches the product")
    public void userFetchesTheProduct() {
        try{
            ResponseEntity<Product> productResponseEntity = productFeignClient.get(productId);
            httpStatusCode = productResponseEntity.getStatusCode().value();
            if(productResponseEntity.getStatusCode().is2xxSuccessful()){
                product = productResponseEntity.getBody();
            }
        } catch (FeignException fe){
            httpStatusCode = fe.status();
        }
    }

    @Then("product has id {long}")
    public void productHasId(long id) {
        assertThat(id).isEqualTo(product.getId());

    }

    @And("response status is {int}")
    public void responseStatusIs(int expectedStatusCode) {
        assertThat(httpStatusCode).isEqualTo(expectedStatusCode);
    }

    @Given("product with id {long} does not exist")
    public void productWithIdDoesNotExist(long id) {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(()-> productService.getById(id));
        productId = id;
    }
}
