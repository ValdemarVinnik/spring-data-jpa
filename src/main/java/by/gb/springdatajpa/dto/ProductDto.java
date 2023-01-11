package by.gb.springdatajpa.dto;

import by.gb.springdatajpa.model.Product;

public class ProductDto {


    private Long id;
    private String title;
    private Long price;

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
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

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

    public ProductDto() {
    }
}
