package com.yusufuzeyir.recipeapp

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yusufuzeyir.recipeapp.databinding.ActivityMainBinding

class GirisSayfasi : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
    lateinit var  binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        //Kullanıcı oturum açtıysa tekrar uygulamaya girdiğinde giriş bilgisi sormaması
        var currentUser=auth.currentUser
        if (currentUser?.email=="ys.kaya1400@gmail.com"){
            startActivity(Intent(this@GirisSayfasi,YoneticiSayfa::class.java))
        }
        else if(currentUser!=null)
        {
            startActivity(Intent(this@GirisSayfasi,ProfilSayfasi::class.java))
            finish()
        }

        //Giriş yap butonuna tıklandığında
        binding.girisyap.setOnClickListener{
            var girisemail=binding.editTextTextEmailAddress.text.toString()
            var girissifre=binding.editTextTextPasswordAsil.text.toString()
            if(TextUtils.isEmpty(girisemail))
            {
                binding.editTextTextEmailAddress.error="Lütfen email adresinisi yazınız."
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(girissifre))
            {
                binding.editTextTextPasswordAsil.error="Lütfen şifrenizi yazınız."
                return@setOnClickListener
            }

            //Giriş bilgilerimizi doğrulayıp giriş yapıyoruz.
            auth.signInWithEmailAndPassword(girisemail,girissifre).addOnCompleteListener(this){
                    if(it.isSuccessful)
                    {

                        // Giren kişi
                        if(binding.editTextTextEmailAddress.text.toString()=="ys.kaya1400@gmail.com" && binding.editTextTextPasswordAsil.text.toString()=="uzox8520")
                        {
                            intent=Intent(applicationContext,YoneticiSayfa::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            intent=Intent(applicationContext,ProfilSayfasi::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Şifre ya da e-mail yanlış, ama hangisi söylemem.",Toast.LENGTH_LONG).show()

                    }
                }
        }
        // Kayit olma sayfasına gitmek için.
        binding.kayitol.setOnClickListener{
            intent=Intent(applicationContext,KayitSayfasi::class.java)
            startActivity(intent)

        }

        // Parolamı unuttum sayfasına geçiş yapmak için.
        binding.sifreunuttum.setOnClickListener{
            intent=Intent(applicationContext,ParolaSifirlamaSayfasi::class.java)
            startActivity(intent)
            finish()
        }

    }

}
