package com.maa.firebaseshoppingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.maa.firebaseshoppingapp.R
import com.maa.firebaseshoppingapp.model.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(val itemList: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(itemList[position].image).resize(1000, 491).into(holder.image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView

        init {
            image= itemView.findViewById(R.id.category_item_image)
        }
    }

}