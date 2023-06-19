package com.yusuf.e_commerceapp.model.network

data class RegisterPostBody(
    val email: String,
    val nom: String ,
    val password: String,
    val prenom: String
)