package com.company.model;

import java.io.Serializable;

public class ProductList implements Serializable {

    private static final long serialVersionUID = 1;

    private String productName;
    private String path;
    private boolean isAvailable;

    public ProductList(String productName, String path, boolean isAvailable) {
        this.productName = productName;
        this.path = path;
        this.isAvailable = isAvailable;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "productName='" + productName + '\'' +
                ", path='" + path + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
