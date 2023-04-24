package com.example.retrofitproject


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface APIInterface {

    @GET("/products")
    fun getAllproducts(): Call<ProductModelClass<Any?>>

    @GET("/products/{id}")
    fun getProductItem(@Path("id") id: Int): Call<ProductsItem>

    @GET("/products/search")
    fun getSearchItem(@Query("q") SearchText: String): Call<ProductModelClass<ProductsItem>>

    @FormUrlEncoded
    @POST("/auth/login")
    fun getLoginData(@Field("username") username: String,@Field("password")password:String): Call<LoginResponse>
}