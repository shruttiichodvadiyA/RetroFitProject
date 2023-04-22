package com.example.retrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitproject.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding
    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        initview()
    }

//    private fun initview() {
//        apiInterface = APIClient.getclient().create(APIInterface::class.java)
//        binding.btnsearch.setOnClickListener {
//            var SearchText=binding.edtsearch.text.toString()
//
//            apiInterface.getSearchItem(SearchText).enqueue(object : Callback<ProductModelClass<ProductsItem>>{
//                override fun onResponse(
//                    call: Call<ProductModelClass<ProductsItem>>,
//                    response: Response<ProductModelClass<ProductsItem>>
//                ) {
//                    var searchlist=response.body()?.products
//                    var adapter=SearchAdapter(this@SearchActivity,searchlist)
//                    var manager=LinearLayoutManager(this@SearchActivity)
//                    binding.rcvsearch.layoutManager=manager
//                    binding.rcvsearch.adapter=adapter
//                }
//
//                override fun onFailure(call: Call<ProductModelClass<ProductsItem>>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//        }
//    }
}
