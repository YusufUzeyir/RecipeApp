package com.yusufuzeyir.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.yusufuzeyir.recipeapp.databinding.ActivityUygulamaIcSayfaBinding


class FavorilerSayfasi : AppCompatActivity() {
    lateinit var binding: ActivityUygulamaIcSayfaBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityUygulamaIcSayfaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        // Profil sayfasına geçiş için
        binding.profilbutton.setOnClickListener{
            intent= Intent(applicationContext,ProfilSayfasi::class.java)
            startActivity(intent)
            finish()
        }

        // Tarifler sayfasına geçiş için
        binding.tarifbutton.setOnClickListener{
            intent= Intent(applicationContext,TariflerSayfasi::class.java)
            startActivity(intent)
            finish()
        }
    }
}