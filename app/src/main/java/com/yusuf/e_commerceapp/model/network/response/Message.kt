package com.yusuf.e_commerceapp.model.network.response

data class Message(
    var cart_id: Int,
    var categorie_id: Int,
    var created_at: String,
    var description: String,
    var id: Int,
    var name: String,
    var picture: String,
    var price: Int,
    var product_id: Int,
    var quantity: Int,
    var updated_at: String
)