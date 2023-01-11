package by.gb.springdatajpa.service;

import by.gb.springdatajpa.model.Product;
import by.gb.springdatajpa.repository.ProductRepository;
import by.gb.springdatajpa.repository.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Page<Product> find(Long maxPrice, Long minPrice, Integer page){
        Specification<Product> spec = Specification.where(null);

        if (maxPrice != null){
            spec.and(ProductSpecification.priceLessOrEqualThen(maxPrice));
        }

        if(minPrice != null){
            spec.and(ProductSpecification.priceGreaterOrEqualThen(minPrice));
        }
        Page<Product> p = repository.findAll(spec, PageRequest.of(page-1,5));
        //return repository.findAll(spec, PageRequest.of(page-1,10));
        return p;
    }


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
