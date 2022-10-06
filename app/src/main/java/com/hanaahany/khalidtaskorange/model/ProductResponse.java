package com.hanaahany.khalidtaskorange.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResponse {

    @SerializedName("products")
    ArrayList<ProductDetails>productDetails;
    private int total;
    private int skip;
    private int limit;

    public ProductResponse(ArrayList<ProductDetails> productDetails, int total, int skip, int limit) {
        this.productDetails = productDetails;
        this.total = total;
        this.skip = skip;
        this.limit = limit;

    }

    public ArrayList<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ArrayList<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "productDetails=" + productDetails +
                ", total=" + total +
                ", skip=" + skip +
                ", limit=" + limit +
                '}';
    }
}
