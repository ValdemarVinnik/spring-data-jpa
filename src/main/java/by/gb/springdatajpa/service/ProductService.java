package by.gb.springdatajpa.service;

import by.gb.springdatajpa.model.Product;
import by.gb.springdatajpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Optional<Product> findProductById(Long id){
        return repository.findById(id);
    }

    public List<Product> findAllProducts() {
        return repository.findAll();

    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProductById(Long id){
        repository.deleteById(id);
    }
}
