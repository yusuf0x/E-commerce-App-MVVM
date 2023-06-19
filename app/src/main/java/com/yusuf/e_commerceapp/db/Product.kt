package com.yusuf.e_commerceapp.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    var id: Int,
    var user_id:Int,
    var name: String,
    var picture: String,
    var price: Int,
    var quantity: Int,
    var updated_at: String,
    var categorie_id: Int,
    var created_at: String,
    var description: String
)