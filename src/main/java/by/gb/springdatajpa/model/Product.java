package by.gb.springdatajpa.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Long price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Product(String title, Long price) {
        this.title = title;
        this.price = price;
    }

    public Product() {
    }
}
