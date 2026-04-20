package com.orsac.gov.villagesathi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.model.BestsellerModel

class BestsellerAdapter(private val bestsellers: List<BestsellerModel>) : RecyclerView.Adapter<BestsellerAdapter.BestsellerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestsellerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bestseller, parent, false)
        return BestsellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestsellerViewHolder, position: Int) {
        val bestseller = bestsellers[position]
        holder.productName.text = bestseller.name
        holder.productPrice.text = bestseller.price
        holder.productImage.setImageResource(bestseller.image)
    }

    override fun getItemCount() = bestsellers.size

    class BestsellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        val productName: TextView = itemView.findViewById(R.id.tvProductName)
        val productPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
    }
}
