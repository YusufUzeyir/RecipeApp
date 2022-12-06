package com.yusufuzeyir.recipeapp


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yusufuzeyir.recipeapp.databinding.YemeksiralamaBinding
import io.grpc.Context


class CustomAdapter(val postList:ArrayList<Post>): RecyclerView.Adapter<CustomAdapter.PostHolder>() {


    class PostHolder(val binding: YemeksiralamaBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        val binding =YemeksiralamaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        holder.binding.yemekadi.text=postList.get(position).TarifAdi //Hangi collection alt?ndaysa o collectiondan Tarif Ad? çekme
        Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.yemekresmi) //Hangi collection alt?ndaysa o collectiondan Tarif Resmi çekme


        val intent=Intent(holder.itemView.context,TarifDetaySayfasi::class.java)

        var yapilislar:String?=postList.get(position).Yapilisi
        var malzemeler:String?=postList.get(position).Malzemeleri //?tem k?sm?ndan tarif malzemesini yazd?rma

        intent.putExtra("malzeme",malzemeler)
        intent.putExtra("yapilis",yapilislar)


        //Card view a bas?ld???nda detay sayfas?n? açma olay?
        holder.itemView.setOnClickListener(){
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}

