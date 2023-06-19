package com.yusuf.e_commerceapp.model.network.response

data class Cart(
    var cart_id: Int,
    var created_at: String,
    var id: Int,
    var product_id: Int,
    var quantity: Int,
    var updated_at: String
)