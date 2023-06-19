package com.yusuf.e_commerceapp.epoxy

import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.databinding.ProductItemBinding
import com.yusuf.e_commerceapp.model.network.response.Product

class ProductItemEpoxyModel (
    val product:Product,
    val onProductSelected:(Product) -> Unit
) : ViewBindingKotlinModel<ProductItemBinding>(R.layout.product_item){

    override fun ProductItemBinding.bind() {
        Picasso.get().load(product.picture).into(productImage);
        itemTitle.text = product.name
        itemPrice.text = product.price.toString() +" USD"
//        itemRating.text = product.
        root.setOnClickListener {
            onProductSelected(product)
        }
    }

}