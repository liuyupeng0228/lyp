package com.fh.shop.backend.api.product;

import java.io.Serializable;

public class ProductApiVo implements Serializable {
    private static final long serialVersionUID = -2984219458806533387L;
    private Integer id;
    private String productName;
    private Double productPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
