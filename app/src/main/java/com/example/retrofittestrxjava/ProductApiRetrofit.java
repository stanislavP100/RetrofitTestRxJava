package com.example.retrofittestrxjava;

import android.graphics.Bitmap;

import java.util.List;
import java.util.ListIterator;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApiRetrofit {

    @GET("retrofittest")
  Observable<List<Product>> getProductsObservable();

    @GET("retrofittest")
    Call<List<Product>> getProducts();

    @GET("get-image")
    Call <Bitmap> getImage (@Query("image") String image);
}
