package com.yusufuzeyir.recipeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.net.toUri
import com.yusufuzeyir.recipeapp.databinding.ActivityTarifDetaySayfasiBinding
import java.net.URI


class TarifDetaySayfasi : AppCompatActivity() {
    lateinit var binding: ActivityTarifDetaySayfasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityTarifDetaySayfasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent=intent
        var malzeme=intent.getStringExtra("malzeme")
        var yapilis=intent.getStringExtra("yapilis")

        binding.detaymalzeme.text=malzeme.toString()
        binding.detayyapilis.text=yapilis.toString()



    }
}


