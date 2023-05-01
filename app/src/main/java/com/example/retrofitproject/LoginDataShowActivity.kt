package com.example.retrofitproject

import android.content.Intent
import android.content.SharedPreferences
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
        logout()
    }

    private fun logout() {
        binding.btnlogout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit: SharedPreferences.Editor = sharedPreferences.edit()
            myEdit.remove("isLogin")
            myEdit.commit()

            var i = Intent(this, LoginPage_Activity::class.java)
            startActivity(i)
            finish()

        }
    }

    private fun initview() {
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        var image = sharedPreferences.getString("image", " ")

        Glide.with(this).load("$image").placeholder(R.drawable.placeholderimg)
            .into(binding.imageview)
        binding.txtusername.text = sharedPreferences.getString("username", " ")
        binding.txtemail.text = sharedPreferences.getString("email", " ")
        binding.txtfirstname.text = sharedPreferences.getString("firstName", " ")
        binding.txtlastname.text = sharedPreferences.getString("lastName", " ")
        binding.txtgender.text = sharedPreferences.getString("gender", " ")


    }
}