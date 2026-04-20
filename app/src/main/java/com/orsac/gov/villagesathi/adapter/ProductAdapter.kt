package com.orsac.gov.villagesathi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.model.ProductModel

class ProductAdapter(
    private val products: List<ProductModel>,
    private val onAddClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.name
        holder.quantity.text = product.quantity
        holder.price.text = product.price
        holder.originalPrice.text = product.originalPrice
        holder.discount.text = product.discount
        holder.productImage.setImageResource(product.image)

        holder.btnAdd.setOnClickListener {
            onAddClick(product)
        }
    }

    override fun getItemCount() = products.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.ivProduct)
        val productName: TextView = itemView.findViewById(R.id.tvProductName)
        val quantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val originalPrice: TextView = itemView.findViewById(R.id.tvOriginalPrice)
        val discount: TextView = itemView.findViewById(R.id.tvDiscount)
        val btnAdd: Button = itemView.findViewById(R.id.btnAdd)
    }
}
