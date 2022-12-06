package com.yusufuzeyir.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.yusufuzeyir.recipeapp.databinding.ActivityMain3Binding


class KayitSayfasi : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    private lateinit var auth: FirebaseAuth
    var databaseReferance:DatabaseReference?=null
    var database:FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReferance=database?.reference!!.child("Profiller")

        //Kaydet butonu ile yeni kullanıcı kayıt etme.
        binding.kayitolbtn.setOnClickListener{
            var uyeIsimSoyisim=binding.isimsoyisim.text.toString()
            var uyeMail=binding.mailadress.text.toString()
            var uyeSifre=binding.sifre.text.toString()
            if(TextUtils.isEmpty(uyeIsimSoyisim))
            {
                binding.isimsoyisim.error="Lütfen isim ve soyisminizi giriniz"
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(uyeMail))
            {
                binding.mailadress.error="Lütfen mail adresinizi giriniz"
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(uyeSifre))
            {
                binding.sifre.error="Lütfen şifrenizi giriniz"
                return@setOnClickListener
            }

            //Email,parola ve kullanıcı bilgilerini veri tabanına ekleme.
            auth.createUserWithEmailAndPassword(binding.mailadress.text.toString(),binding.sifre.text.toString())
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful)
                    {
                        //Şuanki kullanıcı bilgilerini alalım.
                        var currentUser = auth.currentUser

                        //Kullanıcı id'sini alıp o id adı altında adımızı ve soyadımızı kaydedelim.
                        var currentUserDb=currentUser?.let { it1 ->databaseReferance?.child(it1.uid) }
                        currentUserDb?.child("adisoyadi")?.setValue(binding.isimsoyisim.text.toString())
                        currentUserDb?.child("mail")?.setValue((binding.mailadress.text.toString()))
                        Toast.makeText(this@KayitSayfasi,"Kayıt Başarılı",Toast.LENGTH_LONG).show()

                        intent= Intent(applicationContext,ProfilSayfasi::class.java) /* Kayıt başarılı ise kayıt olduktan sonra
                        direkt olarak uygulamanın içine atsın. */
                        startActivity(intent)
                        finish()

                    }
                    else
                    {
                        Toast.makeText(this@KayitSayfasi,"Kayıt Hatalı",Toast.LENGTH_LONG).show()
                    }
                }

        }
        //Giriş sayfasına gitmek için.
        binding.ztnhsp.setOnClickListener{
            auth.signOut()  /*Giriş sayfasında daha önce giriş yapıldıysa direkt olarak uygulama içine attığı için
            kayıt olduktan sonra hesaptan çıkış yaparak giriş sayfasına dönüyoruz. */
            intent= Intent(applicationContext,GirisSayfasi::class.java)
            startActivity(intent)
            finish()
        }





    }
}