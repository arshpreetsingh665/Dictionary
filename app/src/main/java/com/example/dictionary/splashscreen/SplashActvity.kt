package com.example.dictionary.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.R

class SplashActvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_actvity)
//        Handler(Looper.getMainLooper()).postDelayed({
//                                                    val intent=Intent(this,MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        },3000)
    }
}