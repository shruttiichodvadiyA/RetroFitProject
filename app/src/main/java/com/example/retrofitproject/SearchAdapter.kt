package com.example.retrofitproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchAdapter(var context: Context,var  searchlist: ArrayList<ProductsItem>?) : RecyclerView.Adapter<SearchAdapter.myadapter>() {

    class myadapter(v: View) : RecyclerView.ViewHolder(v) {
        var txtid: TextView = v.findViewById(R.id.txtid)
        var txttitle: TextView = v.findViewById(R.id.txttitle)
        var txtprice: TextView = v.findViewById(R.id.txtprice)
        var txtdiscountPercentage: TextView = v.findViewById(R.id.txtdiscountPercentage)
        var txtrating: TextView = v.findViewById(R.id.txtrating)
        var txtdescription: TextView = v.findViewById(R.id.txtdescription)
        var txtstock: TextView = v.findViewById(R.id.txtstock)
        var txtcategory: TextView = v.findViewById(R.id.txtcategory)
        var txtbrand: TextView = v.findViewById(R.id.txtbrand)
        var imgproduct: ImageView = v.findViewById(R.id.imgproduct)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myadapter {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_file, parent, false)
        val adapter = myadapter(v)
        return adapter
    }

    override fun onBindViewHolder(holder: myadapter, position: Int) {
        holder.txtid.setText(searchlist!![position].id.toString())
        holder.txttitle.setText(searchlist!![position].title.toString())
        holder.txtprice.setText(searchlist!![position].price.toString())
        holder.txtrating.setText(searchlist!![position].rating.toString())
        holder.txtdescription.setText(searchlist!![position].description.toString())
        holder.txtstock.setText(searchlist!![position].stock.toString())
        holder.txtcategory.setText(searchlist!![position].category.toString())
        holder.txtbrand.setText(searchlist!![position].brand.toString())
        holder.txtdiscountPercentage.setText(searchlist!![position].discountPercentage.toString())
        Glide.with(context)
            .load("https://i.dummyjson.com/data/products/${searchlist!![position].id}/thumbnail.jpg")
            .placeholder(R.drawable.placeholderimg).into(holder.imgproduct)


    }

    override fun getItemCount(): Int {
        return searchlist!!.size
    }
}