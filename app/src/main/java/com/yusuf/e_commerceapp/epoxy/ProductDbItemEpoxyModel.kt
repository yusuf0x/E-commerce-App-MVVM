package com.yusuf.e_commerceapp.epoxy

import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.databinding.WishlistItemBinding
import com.yusuf.e_commerceapp.db.Product
import java.text.NumberFormat

class ProductDbItemEpoxyModel (
    val product: Product,
    val onProductSelected:(Product) -> Unit,
    val onProductDelete:(Product) -> Unit
) : ViewBindingKotlinModel<WishlistItemBinding>(R.layout.wishlist_item){
    private val currencyFormatter = NumberFormat.getCurrencyInstance()
    override fun WishlistItemBinding.bind() {
        Picasso.get().load(product.picture).into(wishlistImage);
        wishlistImageViewLoadingProgressBar.isVisible = false
        wishlistTitle.text = product.name
        wishlistPrice.text =  currencyFormatter.format(product.price)
        root.setOnClickListener {
            onProductSelected(product)
        }
        wishlistDelete.setOnClickListener {
            onProductDelete(product)
        }
    }

}