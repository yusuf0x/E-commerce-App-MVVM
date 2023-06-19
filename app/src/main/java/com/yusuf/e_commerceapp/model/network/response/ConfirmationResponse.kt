package com.yusuf.e_commerceapp.model.network.response

data class ConfirmationResponse(
    val created_at: String = "",
    val email: String = "",
    val firstname: String = "",
    val id: Int = 0,
    val lastname: String = "",
    val password: String = "",
    val role: String = "",
    val updated_at: String = ""
)