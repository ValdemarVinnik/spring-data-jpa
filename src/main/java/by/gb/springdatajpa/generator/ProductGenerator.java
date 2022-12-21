package by.gb.springdatajpa.generator;

import by.gb.springdatajpa.repository.ProductRepository;
import by.gb.springdatajpa.model.Product;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductGenerator {

    @Autowired
    private ProductRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    private void generateProduct() {
        Faker faker = new Faker();
        for (int i = 0; i <= 10; i++) {
            Product fruit = new Product(faker.food().fruit(), faker.number().numberBetween(1l, 999));
            Product vegetable = new Product(faker.food().vegetable(), faker.number().numberBetween(1l, 999));
            Product ingredient = new Product(faker.food().ingredient(), faker.number().numberBetween(1l, 999));
            repository.save(fruit);
            repository.save(vegetable);
            repository.save(ingredient);
        }
    }
}
