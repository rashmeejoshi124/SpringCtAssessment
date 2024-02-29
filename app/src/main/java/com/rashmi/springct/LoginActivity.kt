package com.rashmi.springct

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rashmi.springct.databinding.ActivityLoginBinding
import com.rashmi.springct.model.LoginCredentials
import com.rashmi.springct.sharedpreferences.AppSharedPreferences
import com.rashmi.springct.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val vm by lazy {  ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setClickListeners()
        supportActionBar?.title = "Login to Spring CT"
    }

    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.email.text.toString() ?: ""
            val password = binding.password.text.toString() ?: ""

            if (email.isNotEmpty() && password.isNotEmpty()) {
                vm.validateCredentials(
                    LoginCredentials(email, password)
                )
            } else {
                Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_LONG).show()
            }
        }

        vm.isLoginSuccess.observe(this) {
            if (it) {
                AppSharedPreferences(this).saveBoolean("LOGGED_IN", true)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}