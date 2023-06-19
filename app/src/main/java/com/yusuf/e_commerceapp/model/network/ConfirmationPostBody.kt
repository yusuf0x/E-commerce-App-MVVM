package com.yusuf.e_commerceapp.model.network

data class ConfirmationPostBody(
    val email: String,
    val nom: String ,
    val password: String,
    val prenom: String
)