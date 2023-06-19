package com.yusuf.e_commerceapp.model.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var categorie_id: Int,
    var created_at: String,
    var description: String,
    var id: Int,
    var name: String,
    var picture: String,
    var price: Int,
    var quantity: Int,
    var updated_at: String
):Parcelable