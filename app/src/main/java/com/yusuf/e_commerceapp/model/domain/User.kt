package com.yusuf.e_commerceapp.model.domain

data class User(
    var birthday: Any?,
    var created_at: String,
    var email: String,
    var firstname: String,
    var id: Int,
    var image: Any?,
    var lastname: String,
    var password: String,
    var remember_token: Any?,
    var role: String,
    var updated_at: String
)