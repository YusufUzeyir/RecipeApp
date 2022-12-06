package com.yusufuzeyir.recipeapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.yusufuzeyir.recipeapp.databinding.ActivityYoneticiSayfaBinding
import java.util.*

class YoneticiSayfa : AppCompatActivity() {

    lateinit var binding: ActivityYoneticiSayfaBinding
    private lateinit var storage: FirebaseStorage
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseFireStore: FirebaseFirestore
    var database:FirebaseDatabase?=null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>


    private var imageuri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoneticiSayfaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        firebaseFireStore = Firebase.firestore
        storage = Firebase.storage
        registerLauncher()
        //inItVars()
        registerClickEvents()


    }

    private fun registerLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intentFromResult = result.data
                    if (intentFromResult != null) {
                        imageuri = intentFromResult.data
                        imageuri.let {
                            binding.yuklenecekresim.setImageURI(it)
                        }
                    }
                }

            }
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->

                if (result) {
                    val intentgaleri =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentgaleri)


                } else {

                }
            }
    }

    private fun registerClickEvents() {

        // Ana yemek ekleme kodları
        binding.anayemekeklebtn.setOnClickListener() {

            val uuıd = UUID.randomUUID()

            val imageName = "$uuıd.jpg"
            val reference = storage.reference
            val imageReference = reference.child("image").child(imageName)
            if (imageuri != null) {
                imageReference.putFile(imageuri!!).addOnSuccessListener {
                    val uploadedPictureReference =
                        storage.reference.child("image").child(imageName)
                    uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                        val downloadUrl = uri.toString()


                        val Map = hashMapOf<String, Any>()
                        Map.put("downloadUrl", downloadUrl)
                        Map.put("TarifAdi", binding.tarifadi.text.toString())
                        Map.put("Yapilisi", binding.yapilisi.text.toString())
                        Map.put("Malzemeleri", binding.malzemeler.text.toString())


                        firebaseFireStore.collection("Ana Yemekler").add(Map).addOnCompleteListener() { task ->
                            if (task.isComplete && task.isSuccessful) {
                                Toast.makeText(this, "Başarılı Yükleme", Toast.LENGTH_LONG).show()

                            }

                        }.addOnFailureListener {
                            Toast.makeText(
                                this@YoneticiSayfa,
                                "Başarısız Yükleme",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, "Storg'a Erişilemiyor", Toast.LENGTH_LONG).show()

                    }
                }
            }


        }

        // Çorba ekleme kodları
        binding.corbaeklebtn.setOnClickListener{

            val uuıd = UUID.randomUUID()

            val imageName = "$uuıd.jpg"
            val reference = storage.reference
            val imageReference = reference.child("image").child(imageName)
            if (imageuri != null) {
                imageReference.putFile(imageuri!!).addOnSuccessListener {
                    val uploadedPictureReference =
                        storage.reference.child("image").child(imageName)
                    uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                        val downloadUrl = uri.toString()


                        val Map = hashMapOf<String, Any>()
                        Map.put("downloadUrl", downloadUrl)
                        Map.put("TarifAdi", binding.tarifadi.text.toString())
                        Map.put("Yapilisi", binding.yapilisi.text.toString())
                        Map.put("Malzemeleri", binding.malzemeler.text.toString())


                        firebaseFireStore.collection("Çorbalar").add(Map).addOnCompleteListener() { task ->
                            if (task.isComplete && task.isSuccessful) {
                                Toast.makeText(this, "Başarılı Yükleme", Toast.LENGTH_LONG).show()

                            }

                        }.addOnFailureListener {
                            Toast.makeText(
                                this@YoneticiSayfa,
                                "Başarısız Yükleme",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, "Storg'a Erişilemiyor", Toast.LENGTH_LONG).show()

                    }
                }
            }


        }


        // Salata ekleme kodları
        binding.salataeklebtn.setOnClickListener{

            val uuıd = UUID.randomUUID()

            val imageName = "$uuıd.jpg"
            val reference = storage.reference
            val imageReference = reference.child("image").child(imageName)
            if (imageuri != null) {
                imageReference.putFile(imageuri!!).addOnSuccessListener {
                    val uploadedPictureReference =
                        storage.reference.child("image").child(imageName)
                    uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                        val downloadUrl = uri.toString()


                        val Map = hashMapOf<String, Any>()
                        Map.put("downloadUrl", downloadUrl)
                        Map.put("TarifAdi", binding.tarifadi.text.toString())
                        Map.put("Yapilisi", binding.yapilisi.text.toString())
                        Map.put("Malzemeleri", binding.malzemeler.text.toString())


                        firebaseFireStore.collection("Salatalar").add(Map).addOnCompleteListener() { task ->
                            if (task.isComplete && task.isSuccessful) {
                                Toast.makeText(this, "Başarılı Yükleme", Toast.LENGTH_LONG).show()

                            }

                        }.addOnFailureListener {
                            Toast.makeText(
                                this@YoneticiSayfa,
                                "Başarısız Yükleme",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, "Storg'a Erişilemiyor", Toast.LENGTH_LONG).show()

                    }
                }
            }


        }

        // Vejeteryen yemek ekleme kodları
        binding.vejeteryenyemekeklebtn.setOnClickListener{

            val uuıd = UUID.randomUUID()

            val imageName = "$uuıd.jpg"
            val reference = storage.reference
            val imageReference = reference.child("image").child(imageName)
            if (imageuri != null) {
                imageReference.putFile(imageuri!!).addOnSuccessListener {
                    val uploadedPictureReference =
                        storage.reference.child("image").child(imageName)
                    uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                        val downloadUrl = uri.toString()


                        val Map = hashMapOf<String, Any>()
                        Map.put("downloadUrl", downloadUrl)
                        Map.put("TarifAdi", binding.tarifadi.text.toString())
                        Map.put("Yapilisi", binding.yapilisi.text.toString())
                        Map.put("Malzemeleri", binding.malzemeler.text.toString())


                        firebaseFireStore.collection("Vejeteryenler").add(Map).addOnCompleteListener() { task ->
                            if (task.isComplete && task.isSuccessful) {
                                Toast.makeText(this, "Başarılı Yükleme", Toast.LENGTH_LONG).show()

                            }

                        }.addOnFailureListener {
                            Toast.makeText(
                                this@YoneticiSayfa,
                                "Başarısız Yükleme",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, "Storg'a Erişilemiyor", Toast.LENGTH_LONG).show()

                    }
                }
            }


        }

        // Tatli ekleme kodları
        binding.tatlieklebtn.setOnClickListener{

            val uuıd = UUID.randomUUID()

            val imageName = "$uuıd.jpg"
            val reference = storage.reference
            val imageReference = reference.child("image").child(imageName)
            if (imageuri != null) {
                imageReference.putFile(imageuri!!).addOnSuccessListener {
                    val uploadedPictureReference =
                        storage.reference.child("image").child(imageName)
                    uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                        val downloadUrl = uri.toString()


                        val Map = hashMapOf<String, Any>()
                        Map.put("downloadUrl", downloadUrl)
                        Map.put("TarifAdi", binding.tarifadi.text.toString())
                        Map.put("Yapilisi", binding.yapilisi.text.toString())
                        Map.put("Malzemeleri", binding.malzemeler.text.toString())


                        firebaseFireStore.collection("Tatlılar").add(Map).addOnCompleteListener() { task ->
                            if (task.isComplete && task.isSuccessful) {
                                Toast.makeText(this, "Başarılı Yükleme", Toast.LENGTH_LONG).show()

                            }

                        }.addOnFailureListener {
                            Toast.makeText(
                                this@YoneticiSayfa,
                                "Başarısız Yükleme",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, "Storg'a Erişilemiyor", Toast.LENGTH_LONG).show()

                    }
                }
            }


        }

        binding.resimsec.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    Snackbar.make(
                        it,
                        "Galeriye erişim izni gerekli",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Give Permissions") {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)


                    }.show()

                } else {

                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }


            } else {
                val intenttogallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intenttogallery)
            }
        }

        // Hesaptan çıkış yapma
        binding.cksyapyoneticibtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this@YoneticiSayfa,GirisSayfasi::class.java))
            finish()
        }


    }
}