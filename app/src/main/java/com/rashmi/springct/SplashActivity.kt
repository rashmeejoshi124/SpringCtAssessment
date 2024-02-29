package com.rashmi.springct

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rashmi.springct.sharedpreferences.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            val isLoggedIn = AppSharedPreferences(this@SplashActivity).getBoolean("LOGGED_IN")
            val launcher = if (isLoggedIn) MainActivity::class.java else LoginActivity::class.java
            val intent = Intent(this@SplashActivity, launcher)
            startActivity(intent)
            finish()
        }
    }
}