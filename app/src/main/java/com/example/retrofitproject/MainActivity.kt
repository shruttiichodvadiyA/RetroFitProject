package com.example.retrofitproject

import android.R
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.EventLogTags
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


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
//        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val netInfo = conMgr.activeNetworkInfo
//        if (netInfo == null) {
//            EventLogTags.Description.setVisibility(View.INVISIBLE)
//            Retrofit.Builder(this)
//                .setTitle(resources.getString(R.string.RetroFitProject))
//                .setMessage(resources.getString(R.string.internet_error))
//                .setPositiveButton("OK", null).show()
//        } else {
//            dialog = ProgressDialog.show(this@WelcomePage, "", "Loading...", true, false)
//            Welcome_Page().execute()
//        }
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