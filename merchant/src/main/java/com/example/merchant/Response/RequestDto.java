package com.example.merchant.Response;

public class RequestDto {
    private Long stock;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {

        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
