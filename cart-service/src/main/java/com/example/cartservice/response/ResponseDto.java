package com.example.cartservice.response;

import java.util.List;

public class ResponseDto {
    private String id;
    private String email;
    private List<ResponseProductDto> productList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ResponseProductDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ResponseProductDto> productList) {
        this.productList = productList;
    }

}
