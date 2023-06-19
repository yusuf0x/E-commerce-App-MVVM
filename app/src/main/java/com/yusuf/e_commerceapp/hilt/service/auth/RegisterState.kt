package com.yusuf.e_commerceapp.hilt.service.auth

import com.yusuf.e_commerceapp.model.network.response.RegisterResponse

sealed class RegisterState {
    class Success(val response: RegisterResponse): RegisterState()
    class Failure(val error: String): RegisterState()
    object Empty: RegisterState()
}