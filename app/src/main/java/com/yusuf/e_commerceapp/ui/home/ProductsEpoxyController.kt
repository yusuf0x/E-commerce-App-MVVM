package com.yusuf.e_commerceapp.ui.home

import com.airbnb.epoxy.EpoxyController
import com.yusuf.e_commerceapp.epoxy.CategoryItemEpoxyModel
import com.yusuf.e_commerceapp.epoxy.ProductItemEpoxyModel
import com.yusuf.e_commerceapp.model.network.response.Categorie
import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import com.yusuf.e_commerceapp.model.network.response.Product

class ProductsEpoxyController (
    val onProductSelected:(Product) -> Unit
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
            ProductItemEpoxyModel(it,onProductSelected).id(it.id).addTo(this)
        }


    }
}