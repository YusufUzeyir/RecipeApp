package com.yusufuzeyir.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.yusufuzeyir.recipeapp.databinding.ActivityTariflerSayfasiBinding

class TariflerSayfasi : AppCompatActivity() {
    lateinit var binding: ActivityTariflerSayfasiBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityTariflerSayfasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()


        // Profil sayfaya geçiş için
        binding.profilbutton.setOnClickListener{
            intent= Intent(applicationContext,ProfilSayfasi::class.java)
            startActivity(intent)
            finish()
        }

        // Ana yemek sayfasına geçiş için
        binding.anayemek.setOnClickListener{
            intent= Intent(applicationContext,AnaYemekSayfasi::class.java)
            startActivity(intent)

        }

        // Çorbalar sayfasına geçiş için
        binding.corbalar.setOnClickListener{
            intent= Intent(applicationContext,CorbalarSayfasi::class.java)
            startActivity(intent)

        }

        // Salatalar sayfasına geçiş için
        binding.salatalar.setOnClickListener{
            intent= Intent(applicationContext,SalatalarSayfasi::class.java)
            startActivity(intent)

        }

        // Vejeteryen sayfasına geçiş için
        binding.vejeteryen.setOnClickListener{
            intent= Intent(applicationContext,VejeteryenSayfasi::class.java)
            startActivity(intent)

        }

        // Tatlılar sayfasına geçiş için
        binding.tatlilar.setOnClickListener{
            intent= Intent(applicationContext,TatlilarSayfasi::class.java)
            startActivity(intent)

        }


    }
}