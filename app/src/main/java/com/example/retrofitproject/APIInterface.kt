package com.example.retrofitproject


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIInterface {

    @GET("/products")
    fun getAllproducts(): Call<ProductModelClass<Any?>>

    @GET("/products/{id}")
    fun getProductItem(@Path("id") id: Int): Call<ProductsItem>

    @GET("/products/search")
    fun getSearchItem(@Query("q") SearchText: String): Call<ProductModelClass<ProductsItem>>

}