package com.yusuf.e_commerceapp.epoxy

import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.databinding.CategoryItemBinding

class CategoryItemEpoxyModel (val name:String) : ViewBindingKotlinModel<CategoryItemBinding>(R.layout.category_item){

    override fun CategoryItemBinding.bind() {
        chip1.text  = name
    }

}