package com.example.foodRecipes.presentation.adapters.holder

import coil.load
import com.example.foodRecipes.data.models.Category
import com.example.foodRecipes.databinding.ItemCategoryBinding

class CategoryHolder(
    private val binding: ItemCategoryBinding
) : SimpleViewHolder<Category>(binding.root) {

    override fun onBind(item: Category) = with(binding) {
        categoryName.text = item.strCategory
        categoryImage.load(item.strCategoryThumb)
    }
}