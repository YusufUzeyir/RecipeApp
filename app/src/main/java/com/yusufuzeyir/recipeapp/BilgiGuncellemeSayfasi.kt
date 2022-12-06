package com.yusufuzeyir.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.yusufuzeyir.recipeapp.databinding.ActivityBilgiGuncellemeSayfasiBinding

class BilgiGuncellemeSayfasi : AppCompatActivity() {
    lateinit var binding:ActivityBilgiGuncellemeSayfasiBinding
    private lateinit var  auth: FirebaseAuth
    var databaseReference:DatabaseReference?=null
    var database:FirebaseDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityBilgiGuncellemeSayfasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("Profiller")

        // Real-time database deki maile erişip edittexte yazdırdık
        var currentUser=auth.currentUser
        binding.mailguncelle.setText(currentUser?.email)

        // Real-time database deki iD erişip adı ve soyadı çekme
        var userReference=databaseReference?.child(currentUser?.uid!!)
        userReference?.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.isimguncelle.setText(snapshot.child("adisoyadi").value.toString())

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        // Bilgilerimi güncelle button

        //Mail güncelleme
        binding.guncellebtn.setOnClickListener {
          var mailguncelle=binding.mailguncelle.text.toString().trim()
            currentUser!!.updateEmail(mailguncelle)
                .addOnCompleteListener{task ->
                  if(task.isSuccessful)
                  {
                      Toast.makeText(applicationContext,"E-mail güncellendi",Toast.LENGTH_LONG).show()
                  }
                    else
                  {
                      Toast.makeText(applicationContext,"E-mail güncelleme başarısız",Toast.LENGTH_LONG).show()
                  }
                }

            //Parola güncelleme
            var sifreguncelle=binding.sifreguncelle.text.toString().trim()
            currentUser!!.updatePassword(sifreguncelle)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"Şifre güncellendi",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Şifre güncelleme başarısız",Toast.LENGTH_LONG).show()
                    }
                }

            //Ad Soyad güncelleme
            var currentUserDb=currentUser?.let { it1 ->databaseReference?.child(it1.uid) }
            currentUserDb?.removeValue()
            currentUserDb?.child("adisoyadi")?.setValue(binding.isimguncelle.text.toString())
            Toast.makeText(applicationContext,"İsim ve soyisim güncellendi",Toast.LENGTH_LONG).show()

            currentUserDb?.child("mail")?.setValue(binding.mailguncelle.text.toString())

        }

    }
}