package com.example.retrofitproject

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitproject.databinding.ActivityLoginPageBinding
import com.example.retrofitproject.databinding.DialogItemFileBinding
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
        password()
    }

    private fun password() {
        binding.chkpassword.setOnClickListener {

        }
    }

    private fun initview() {
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLogin", false) == true) {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.btnlogin.setOnClickListener {

            val dialog = Dialog(this)
            val dialogItemFileBinding = DialogItemFileBinding.inflate(layoutInflater)
            dialog.setContentView(dialogItemFileBinding.root)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.show()

            val username = binding.edtusername.text.toString()
            val password = binding.edtpassword.text.toString()
            apiInterface = APIClient.getclient().create(APIInterface::class.java)
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

                            if (response.isSuccessful && response.code() == 200) {
                                Log.e("TAG", "login: " + username)
                                var username = response.body()?.username
//                                var id = response.body()?.id
                                var email = response.body()?.email
                                var firstName = response.body()?.firstName
                                var lastName = response.body()?.lastName
                                var gender = response.body()?.gender
                                var image = response.body()?.image
                                var token = response.body()?.token


                                val myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                                myEdit.putBoolean("isLogin", true)
                                myEdit.putString("username", username)
                                myEdit.putString("firstName", firstName)
                                myEdit.putString("lastName", lastName)
                                myEdit.putString("gender", gender)
                                myEdit.putString("image", image)
                                myEdit.putString("email", email)
//                                myEdit.apply()
                                myEdit.commit()

                                var intent =
                                    Intent(this@LoginPage_Activity, MainActivity::class.java)
                                startActivity(intent)
                                dialog.dismiss()
                                Log.e("TAG", "onResponse: " + gender)
                                var i = Intent(
                                    this@LoginPage_Activity,
                                    MainActivity::class.java
                                )
                                i.putExtra("username", username)
//                                i.putExtra("id", id)
                                i.putExtra("email", email)
                                i.putExtra("firstName", firstName)
                                i.putExtra("lastName", lastName)
                                i.putExtra("gender", gender)
                                i.putExtra("image", image)
                                i.putExtra("token", token)

                                Toast.makeText(
                                    this@LoginPage_Activity,
                                    "login is successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(i)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginPage_Activity,
                                    "password and username is incorrect",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                        }

                    })
            }
        }
    }


}