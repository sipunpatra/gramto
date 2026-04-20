package com.orsac.gov.villagesathi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.model.CategoryGroupModel
import com.orsac.gov.villagesathi.model.CategoryModel

class CategoryGroupAdapter(
    private val categoryGroups: List<CategoryGroupModel>,
    private val onItemClick: (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryGroupAdapter.CategoryGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryGroupViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_group, parent, false)
        return CategoryGroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryGroupViewHolder, position: Int) {
        val categoryGroup = categoryGroups[position]
        holder.groupTitle.text = categoryGroup.title
        holder.subCategories.apply {
            adapter = CategoryAdapter(categoryGroup.categories, onItemClick)
        }
    }

    override fun getItemCount() = categoryGroups.size

    class CategoryGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupTitle: TextView = itemView.findViewById(R.id.tvGroupTitle)
        val subCategories: RecyclerView = itemView.findViewById(R.id.rvSubCategories)
    }
}
