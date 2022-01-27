package com.example.cartservice.dto;

public class Product {
    private String productId;
    private int quantity;
    private String merchantId;
    private Long price;

    public Product(String productId, int quantity, String merchantId, Long price) {
        this.productId = productId;
        this.quantity = quantity;
        this.merchantId = merchantId;
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
