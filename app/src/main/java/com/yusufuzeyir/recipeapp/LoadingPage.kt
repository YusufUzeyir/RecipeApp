package com.yusufuzeyir.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class LoadingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_page)

        supportActionBar?.hide()

        val timer = object: CountDownTimer(3500, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {

                val intent=Intent(this@LoadingPage,GirisSayfasi::class.java)
                startActivity(intent)

            }
        }
        timer.start()

    }
}