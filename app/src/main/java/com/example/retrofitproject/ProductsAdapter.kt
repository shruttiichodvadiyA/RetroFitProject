package com.example.retrofitproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductsAdapter(var context: Context, var  productlist: ArrayList<ProductsItem>?, var invoke:((ProductsItem)->Unit)) : RecyclerView.Adapter<ProductsAdapter.myadapter>() {

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
        var layoutproducts: LinearLayout = v.findViewById(R.id.layoutproducts)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myadapter {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.products_item_file, parent, false)
        val adapter = myadapter(v)
        return adapter
    }

    override fun onBindViewHolder(holder: myadapter, position: Int) {
        holder.txtid.setText(productlist!![position].id.toString())
        holder.txttitle.setText(productlist!![position].title.toString())
        holder.txtprice.setText(productlist!![position].price.toString())
        holder.txtrating.setText(productlist!![position].rating.toString())
        holder.txtdescription.setText(productlist!![position].description.toString())
        holder.txtstock.setText(productlist!![position].stock.toString())
        holder.txtcategory.setText(productlist!![position].category.toString())
        holder.txtbrand.setText(productlist!![position].brand.toString())
        holder.txtdiscountPercentage.setText(productlist!![position].discountPercentage.toString())
        Glide.with(context)
            .load("https://i.dummyjson.com/data/products/${productlist!![position].id}/thumbnail.jpg")
            .placeholder(R.drawable.placeholderimg).into(holder.imgproduct)

        holder.layoutproducts.setOnClickListener {
            invoke.invoke(productlist!![position])
        }
    }

    override fun getItemCount(): Int {
        return productlist!!.size
    }
}