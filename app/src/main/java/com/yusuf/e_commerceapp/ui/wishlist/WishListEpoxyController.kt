package com.yusuf.e_commerceapp.ui.wishlist

import com.airbnb.epoxy.EpoxyController
import com.yusuf.e_commerceapp.db.Product
import com.yusuf.e_commerceapp.epoxy.ProductDbItemEpoxyModel

class WishListEpoxyController (
    val onProductSelected:(Product) -> Unit,
    val onProductDelete:(Product) -> Unit
) : EpoxyController() {
    var isLoading :Boolean = true
        set(value) {
            field = value
            if(field){
                requestModelBuild()
            }
        }

    var products:List<Product> = emptyList()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }
    override  fun buildModels() {
        if(isLoading){
            return
        }
        if (products.isEmpty()) {
            return
        }

        products.forEach {
            ProductDbItemEpoxyModel(it,onProductSelected,onProductDelete).id(it.id).addTo(this)
        }


    }
}