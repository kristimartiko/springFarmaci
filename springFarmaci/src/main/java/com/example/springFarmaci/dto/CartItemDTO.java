package com.example.springFarmaci.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

public class CartItemDTO implements Serializable {
    public Long id;
    public String name;
    public double price;
    public Integer quantity;
    public String img;
    public Integer stockQuantity;

    public CartItemDTO(Long id, String name, double price, Integer quantity,
                       String img, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.stockQuantity = stockQuantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }
}

