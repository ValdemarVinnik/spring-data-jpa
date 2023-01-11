package by.gb.springdatajpa.repository.specifications;

import by.gb.springdatajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecification {

    public static Specification<Product> priceGreaterOrEqualThen(Long price){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessOrEqualThen(Long price){
        Specification<Product> spec = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),price);
        return spec;
    }
}
