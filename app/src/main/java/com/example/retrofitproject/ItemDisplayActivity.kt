package com.example.retrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitproject.databinding.ActivityItemDisplayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDisplayActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemDisplayBinding
    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    private fun initview() {

        var id=intent.getIntExtra("id",0)

        apiInterface = APIClient.getclient().create(APIInterface::class.java)

        apiInterface.getProductItem(id).enqueue(object :Callback<ProductsItem>{
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {
                var images=response.body()?.images
                var id=response.body()?.id
                var title=response.body()?.title
                var description=response.body()?.description
                var price=response.body()?.price
                var discountPercentage=response.body()?.discountPercentage
                var rating=response.body()?.rating
                var brand=response.body()?.brand
                var category=response.body()?.category
                var stock=response.body()?.stock

                binding.txtTitleproduct.text=title
                binding.txtid.text=id.toString()
                binding.txtdescription.text=description
                binding.txtprice.text=price.toString()
                binding.txtdiscountPercentage.text=discountPercentage.toString()
                binding.txtrating.text=rating.toString()
                binding.txtbrand.text=brand
                binding.txtcategory.text=category
                binding.txtstock.text=stock.toString()

                var adapter=ImageAdapter(this@ItemDisplayActivity,images)
                binding.viewpager.adapter=adapter

            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
            }

        })

    }
}