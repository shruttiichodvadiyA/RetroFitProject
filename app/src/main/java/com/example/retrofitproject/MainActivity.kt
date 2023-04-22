package com.example.retrofitproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
        search()
    }


    private fun initview() {

        apiInterface = APIClient.getclient().create(APIInterface::class.java)


        apiInterface.getAllproducts().enqueue(object : retrofit2.Callback<ProductModelClass<Any?>> {
            override fun onResponse(
                call: Call<ProductModelClass<Any?>>,
                response: Response<ProductModelClass<Any?>>
            ) {
                var productlist = response.body()?.products

                var adapter = ProductsAdapter(this@MainActivity, productlist) {

                    var i = Intent(this@MainActivity, ItemDisplayActivity::class.java)
                    i.putExtra("id", it.id)
                    Log.e("TAG", "onResponse: " + it.id)
                    startActivity(i)
                }

                Log.e("TAG", "onResponse: " + productlist?.size)

                var manager = LinearLayoutManager(this@MainActivity)
                binding.rcvproducts.layoutManager = manager
                binding.rcvproducts.adapter = adapter
            }

            override fun onFailure(call: Call<ProductModelClass<Any?>>, t: Throwable) {

            }

        })

    }

    private fun search() {
        binding.btnsearch.setOnClickListener {
            var SearchText=binding.edtsearch.text.toString()

            apiInterface.getSearchItem(SearchText).enqueue(object :
                Callback<ProductModelClass<ProductsItem>> {
                override fun onResponse(
                    call: Call<ProductModelClass<ProductsItem>>,
                    response: Response<ProductModelClass<ProductsItem>>
                ) {
                    var searchlist=response.body()?.products
                    var adapter=SearchAdapter(this@MainActivity,searchlist)
                    var manager=LinearLayoutManager(this@MainActivity)
                    binding.rcvsearch.layoutManager=manager
                    binding.rcvsearch.adapter=adapter
                }

                override fun onFailure(call: Call<ProductModelClass<ProductsItem>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

}