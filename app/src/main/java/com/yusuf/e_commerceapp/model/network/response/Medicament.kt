package com.yusuf.e_commerceapp.model.network.response

data class Medicament(
    var categorie_id: Int,
    var created_at: String,
    var description: String,
    var id: Int,
    var name: String,
    var picture: String,
    var price: Int,
    var quantity: Int,
    var updated_at: String
)