package com.example.order.Request;

import com.example.order.dto.OrderedProductsDto;

import java.util.List;

public class RequestDto {
    private String userId;
    private String timeStamp;
    private Long total;
    private List<OrderedProductsDto> products;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<OrderedProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProductsDto> products) {
        this.products = products;
    }
}
