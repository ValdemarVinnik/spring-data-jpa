package by.gb.springdatajpa.api;

import by.gb.springdatajpa.model.Product;
import by.gb.springdatajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable Long id) {
        Optional<Product> optional = service.findProductById(id);
        return optional.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @GetMapping()
    private List<Product> findAllProduct(@RequestParam Integer minPrice, @RequestParam Integer maxPrice) {

        List<Product> list = service.findAllProducts();

        if (list == null | list.size() == 0) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        List<Product> productList = list.stream()
                .filter(p -> p.getPrice() >= minPrice)
                .filter(product -> product.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        return productList;
    }


    @PostMapping("/add")
    private Product addProduct(@RequestParam String title, @RequestParam Long price) {
        return service.saveProduct(new Product(title, price));
    }

    @GetMapping("/delete/{id}")
    private void deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
    }

}
