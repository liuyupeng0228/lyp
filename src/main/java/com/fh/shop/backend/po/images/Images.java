package com.fh.shop.backend.po.images;

import java.io.Serializable;

public class Images implements Serializable {

    private static final long serialVersionUID = 2961393098926681319L;
    private Integer id;
    private String imagePath;
    private Integer productId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
