package com.yusuf.e_commerceapp.model.network.response

data class CategoryResponse(
    var created_at: String,
    var description: String,
    var id: Int,
    var name: String,
    var picture: String,
    var updated_at: String
)