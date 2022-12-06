package com.yusufuzeyir.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yusufuzeyir.recipeapp.databinding.ActivitySalatalarSayfasiBinding

class SalatalarSayfasi : AppCompatActivity() {
    private lateinit var binding: ActivitySalatalarSayfasiBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var PostArrayList: ArrayList<Post>
    private lateinit var feedadapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalatalarSayfasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore

        PostArrayList = ArrayList<Post>()

        getdata()

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        feedadapter = CustomAdapter(PostArrayList)
        binding.recyclerview.adapter = feedadapter


    }

    private fun getdata() {
        db.collection("Salatalar").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {


                        val documents = value.documents
                        for (document in documents) {
                            val TarifAdi = document.get("TarifAdi") as String
                            val Malzemeleri=document.get("Malzemeleri") as String
                            val downloadUrl = document.get("downloadUrl") as String
                            val Yapilisi=document.get("Yapilisi") as String


                            val post = Post(TarifAdi, Malzemeleri,downloadUrl,Yapilisi)

                            PostArrayList.add(post)

                        }
                        feedadapter.notifyDataSetChanged()
                    }
                }
            }


        }
    }
}