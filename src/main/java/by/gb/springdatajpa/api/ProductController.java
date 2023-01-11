package by.gb.springdatajpa.api;

import by.gb.springdatajpa.dto.ProductDto;
import by.gb.springdatajpa.model.Product;
import by.gb.springdatajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable Long id) {
        Optional<Product> optional = service.findProductById(id);
        return optional.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }


    @GetMapping
    private Page<ProductDto> findAllProduct(
            @RequestParam(name = "minPrice", required = false) Long minPrice,
            @RequestParam(name = "maxPrice", required = false) Long maxPrice,
            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        if (page < 1) {
            page = 1;
        }

        return service.find(maxPrice, minPrice, page).map(ProductDto::new);
    }


    //    @PostMapping
//    private Product saveProduct(@RequestParam String title, @RequestParam Long price) {
//
//        return service.saveProduct(new Product(title, price));
//    }

    @PostMapping
    private Product saveProduct(@RequestBody ProductDto productDto) {
        return service.saveProduct( new Product(productDto.getTitle(), productDto.getPrice()));
    }

    @PutMapping
    private Product updateProduct(@RequestParam String title, @RequestParam Long price) {
        return service.saveProduct(new Product(title, price));
    }

    @DeleteMapping("/{id}")
    private void deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
    }

}
