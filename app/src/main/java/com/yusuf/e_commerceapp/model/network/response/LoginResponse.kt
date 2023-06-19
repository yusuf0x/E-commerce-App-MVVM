package com.yusuf.e_commerceapp.model.network.response

data class LoginResponse(
    val birthday: Any? = Any(),
    val created_at: String = "",
    val email: String = "",
    val firstname: String = "",
    val id: Int = 0,
    val image: Any? = Any(),
    val lastname: String = "",
    val password: String = "",
    val remember_token: Any? = Any(),
    val role: String = "",
    val updated_at: String = ""
)