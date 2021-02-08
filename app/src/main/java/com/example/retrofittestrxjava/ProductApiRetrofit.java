package com.example.retrofittestrxjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApiRetrofit {

    @GET("retrofittest")
  Call<List <Product>> getProducts();
}
