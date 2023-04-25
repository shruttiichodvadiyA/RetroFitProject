package com.example.retrofitproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitproject.databinding.ActivityLoginPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPage_Activity : AppCompatActivity() {
    lateinit var binding: ActivityLoginPageBinding
    lateinit var apiInterface: APIInterface



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }
    private fun initview() {
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLogin",false)==true){
            var i =Intent(this,LoginDataShowActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.btnlogin.setOnClickListener {


//            val myEdit :SharedPreferences.Editor= sharedPreferences.edit()
//            myEdit.putBoolean("isLogin", true)
//            myEdit.commit()

//            var i =Intent(this,LoginDataShowActivity::class.java)
//            startActivity(i)

            apiInterface = APIClient.getclient().create(APIInterface::class.java)
            var username = binding.edtusername.text.toString()
            var password = binding.edtpassword.text.toString()
            if (username.isEmpty()) {
                Toast.makeText(this, "Plaese enter your username", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Plaese enter your password", Toast.LENGTH_SHORT).show()
            } else {
                apiInterface.getLoginData(username, password)
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {

                            if (response.isSuccessful && response.code()==200) {
                                Log.e("TAG", "login: " + username)
                                var username = response.body()?.username
//                                var id = response.body()?.id
                                var email = response.body()?.email
                                var firstName = response.body()?.firstName
                                var lastName = response.body()?.lastName
                                var gender = response.body()?.gender
                                var image = response.body()?.image
                                var token = response.body()?.token

                                val myEdit :SharedPreferences.Editor= sharedPreferences.edit()
                                myEdit.putBoolean("isLogin", true)
                                myEdit.putString("username",username)
                                myEdit.putString("firstName",firstName)
                                myEdit.putString("lastName",lastName)
                                myEdit.putString("gender",gender)
                                myEdit.putString("image",image)
                                myEdit.putString("email",email)
                                myEdit.commit()

                                var intent =Intent(this@LoginPage_Activity,LoginDataShowActivity::class.java)
                                startActivity(intent)

                                Log.e("TAG", "onResponse: " + gender)
                                var i = Intent(this@LoginPage_Activity, LoginDataShowActivity::class.java)
                                i.putExtra("username", username)
//                                i.putExtra("id", id)
                                i.putExtra("email", email)
                                i.putExtra("firstName", firstName)
                                i.putExtra("lastName", lastName)
                                i.putExtra("gender", gender)
                                i.putExtra("image", image)
                                i.putExtra("token", token)

                                Toast.makeText(this@LoginPage_Activity, "login is successfully", Toast.LENGTH_SHORT).show()
                                startActivity(i)
                                finish()
                            } else {
                                Toast.makeText(this@LoginPage_Activity, "password and username is incorrect", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                        }

                    })
            }
        }
    }



}