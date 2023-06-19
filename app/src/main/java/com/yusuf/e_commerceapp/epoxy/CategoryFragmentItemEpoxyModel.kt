package com.yusuf.e_commerceapp.epoxy

import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.databinding.CategoryFragmentItemBinding
import com.yusuf.e_commerceapp.databinding.CategoryItemBinding
import com.yusuf.e_commerceapp.model.network.response.Categorie

class CategoryFragmentItemEpoxyModel (val categorie: Categorie) : ViewBindingKotlinModel<CategoryFragmentItemBinding>(R.layout.category_fragment_item){

    override fun CategoryFragmentItemBinding.bind() {
        text1.text = categorie.name
    }

}