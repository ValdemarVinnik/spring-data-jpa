package by.gb.springdatajpa.api;

import by.gb.springdatajpa.model.Product;
import by.gb.springdatajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable Long id){
        Optional<Product> optional = service.findProductById(id);
        return optional.orElseThrow(() ->new ResponseStatusException(NOT_FOUND));
    }

    @GetMapping()
    private List<Product> findAllProduct(){
        List<Product> list = service.findAllProducts();

        if (list == null | list.size()==0){
             throw new ResponseStatusException(NOT_FOUND);
        }
        return list;
    }

    @PostMapping("/post")
    private void addProduct(@PathVariable Product product){
        service.saveProduct(product);
    }

    @GetMapping("/delete/{id}")
    private void deleteProductById(@PathVariable Long id){
        service.deleteProductById(id);
    }

}
