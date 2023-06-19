package com.yusuf.e_commerceapp.ui.home

import com.airbnb.epoxy.EpoxyController
import com.yusuf.e_commerceapp.epoxy.CategoryItemEpoxyModel
import com.yusuf.e_commerceapp.model.network.response.Categorie
import com.yusuf.e_commerceapp.model.network.response.Product

class CategoriesEpoxyController () : EpoxyController() {
    var isLoading :Boolean = true
        set(value) {
            field = value
            if(field){
                requestModelBuild()
            }
        }
    var items:List<Categorie> = emptyList()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override  fun buildModels() {
        if(isLoading){
            return
        }
        if (items.isEmpty()) {
            return
        }
        items.forEach{
            CategoryItemEpoxyModel(it.name).id(it.name).addTo(this)
        }



    }
}