package com.example.retrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.retrofitproject.databinding.ActivityLoginDataShowBinding

class LoginDataShowActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginDataShowBinding
//    lateinit var apiInterface: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginDataShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        apiInterface = APIClient.getclient().create(APIInterface::class.java)
        initview()
    }
    private fun initview() {
        var username=intent.getStringExtra("username")
//        var id=intent.getIntExtra("id")
        var email=intent.getStringExtra("email")
        var firstName=intent.getStringExtra("firstName")
        var lastName=intent.getStringExtra("lastName")
        var gender=intent.getStringExtra("gender")
        var image=intent.getStringExtra("image")
        var token=intent.getStringExtra("token")

        Glide.with(this).load("$image").placeholder(R.drawable.placeholderimg).into(binding.imageview)
        binding.txtusername.text=username
        binding.txtemail.text=email
//        binding.txtid.text=id.toString()
        binding.txtfirstname.text=firstName
        binding.txtlastname.text=lastName
        binding.txtgender.text=gender

    }
}