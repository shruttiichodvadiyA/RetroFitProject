package com.example.retrofitproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        binding.btnlogin.setOnClickListener {
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