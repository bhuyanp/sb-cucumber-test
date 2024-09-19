package product;

import com.example.sbtest.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@FeignClient(name="PRODUCT-SERVICE",url = "${feign.client.url}")
public interface ProductFeignClient {
    @GetMapping("/api/products")
    ResponseEntity<List<Product>> getProducts();

    @GetMapping("/api/products/{id}")
    ResponseEntity<Product> get(@PathVariable long id);

    @PostMapping("/api/products")
    ResponseEntity<Product> save(@RequestBody Product product);
}