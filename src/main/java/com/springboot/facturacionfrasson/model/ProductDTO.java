package com.springboot.facturacionfrasson.model;

import jakarta.persistence.Column;

public class ProductDTO {

    private int product_id;
    private String title;
    private String description;
    private String code;
    private double price;
    private int stock;

    public ProductDTO(int product_id, String title, String description, String code, double price, int stock) {
        this.product_id = product_id;
        this.title = title;
        this.description = description;
        this.code = code;
        this.price = price;
        this.stock = stock;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "product_id=" + product_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", stock=" + stock;
    }
}
