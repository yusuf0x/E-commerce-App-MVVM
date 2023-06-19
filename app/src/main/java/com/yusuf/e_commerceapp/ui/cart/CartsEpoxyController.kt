package com.yusuf.e_commerceapp.ui.cart

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.yusuf.e_commerceapp.epoxy.CartItemEpoxyModel
import com.yusuf.e_commerceapp.epoxy.CategoryItemEpoxyModel
import com.yusuf.e_commerceapp.epoxy.ProductItemEpoxyModel
import com.yusuf.e_commerceapp.model.network.response.Categorie
import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import com.yusuf.e_commerceapp.model.network.response.Message
import com.yusuf.e_commerceapp.model.network.response.Product

class CartsEpoxyController (
) : EpoxyController() {
    var isLoading :Boolean = true
        set(value) {
            field = value
            if(field){
                requestModelBuild()
            }
        }

    var carts:List<Message> = emptyList()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }
    override  fun buildModels() {
        if(isLoading){
            return
        }
        if (carts.isEmpty()) {
            return
        }

        carts.forEach {
            CartItemEpoxyModel(
                it,
                onQuantityChanged = { quantity ->
                    it.quantity = quantity
                    Log.d("TAG",quantity.toString())
                }

            ).id(it.id).addTo(this)
        }


    }
}