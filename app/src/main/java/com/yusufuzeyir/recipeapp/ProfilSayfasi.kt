package com.yusufuzeyir.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.yusufuzeyir.recipeapp.databinding.ActivityProfilSayfasiBinding

class ProfilSayfasi : AppCompatActivity() {
    lateinit var binding: ActivityProfilSayfasiBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference:DatabaseReference?=null
    var database:FirebaseDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityProfilSayfasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("Profiller")

        //Email bilgisini çekme
        var currentUser=auth.currentUser
        binding.profilemail.text="Email: "+currentUser?.email

        //realtime-database deki id ye ulaşıp altındaki child ların içindeki veriyi sayfaya aktarma
        var userReference=databaseReference?.child(currentUser?.uid!!)
        userReference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot)
            {
             binding.profifisimsoyisim.text="Tam adınız: "+snapshot.child("adisoyadi").value.toString()
            }

            override fun onCancelled(error: DatabaseError)
            {
                TODO("Not yet implemented")
            }

        })

        // Hesaptan çıkış yapma
        binding.cikissyapbutton.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this@ProfilSayfasi,GirisSayfasi::class.java))
            finish()
        }

        // Hesabı Silme
        binding.hesapsilbutton.setOnClickListener{

             //Authentication kısmından verileri silme
              currentUser?.delete()?.addOnCompleteListener{
                  if(it.isSuccessful){
                      Toast.makeText(applicationContext,"Hesabınız Silindi",Toast.LENGTH_LONG).show()
                      auth.signOut()
                      startActivity(Intent(this@ProfilSayfasi,GirisSayfasi::class.java))
                      finish()

                      //Realtime Database kısmından bilgileri silme
                      var currentUserDb=currentUser?.let { it1 ->databaseReference!!.child(it1.uid) }
                      currentUserDb?.removeValue()
                  }
              }
        }


        // Tarifler sayfaya geçiş için
        binding.tarifbutton.setOnClickListener{
            intent= Intent(applicationContext,TariflerSayfasi::class.java)
            startActivity(intent)
            finish()
        }

        // Güncelleme sayfasina geçiş için
        binding.bilgiguncelle.setOnClickListener{
            intent= Intent(applicationContext,BilgiGuncellemeSayfasi::class.java)
            startActivity(intent)
            finish()
        }
    }
}