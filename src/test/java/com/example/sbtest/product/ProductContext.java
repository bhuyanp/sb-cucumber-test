package com.example.sbtest.product;

import com.example.sbtest.Product;
import com.example.sbtest.common.CommonContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Getter
@Setter
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ProductContext extends CommonContext {
    private long productId;
    private Product product;
}
