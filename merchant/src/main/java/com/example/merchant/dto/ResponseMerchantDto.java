package com.example.merchant.dto;

import javax.swing.text.Document;

public class ResponseMerchantDto {
    private Double price;
    private String merchantId;
    private String prodcutId;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProdcutId() {
        return prodcutId;
    }

    public void setProdcutId(String prodcutId) {
        this.prodcutId = prodcutId;
    }
}
