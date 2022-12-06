package com.yusufuzeyir.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.yusufuzeyir.recipeapp.databinding.ActivityPsifirlaBinding

class ParolaSifirlamaSayfasi : AppCompatActivity() {
    lateinit var binding:ActivityPsifirlaBinding
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityPsifirlaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.parolasifirlabtn.setOnClickListener{
            var psifirlaemail = binding.sifirlamamail.text.toString().trim()
            if(TextUtils.isEmpty((psifirlaemail)))
            {
                binding.sifirlamamail.error="Lütfen e-mail adresinizi yazınız."
            }
            else
            {
                auth.sendPasswordResetEmail(psifirlaemail)
                    .addOnCompleteListener(this){ sifirlama ->
                        if(sifirlama.isSuccessful){
                            binding.sifirlamamesaj.text="e-mail adresinize sıfırlama bağlantısı gönderildi, lütfen kontrol ediniz."
                        }
                        else
                        {
                            binding.sifirlamamesaj.text="Sıfırlama işlemi başarısız"
                        }
                    }
            }
        }

        //Giriş sayfasına gitmek için
        binding.sifirlamagirisyap.setOnClickListener{
            intent=Intent(applicationContext,GirisSayfasi::class.java)
            startActivity(intent)
            finish()
        }

    }

}