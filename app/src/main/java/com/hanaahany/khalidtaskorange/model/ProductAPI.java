package com.hanaahany.khalidtaskorange.model;

import com.hanaahany.khalidtaskorange.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductAPI {

    @GET("products")
    Call<ProductResponse>getProduct();

    @GET("products/{id}")
    Call<ProductDetails>getProductDetails(@Path("id") int id);
}
