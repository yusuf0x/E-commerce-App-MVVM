package com.yusuf.e_commerceapp.hilt.service.auth

import com.yusuf.e_commerceapp.model.network.response.LoginResponse

sealed class LoginState {
    class Success(val response: LoginResponse): LoginState()
    class Failure(val error: String): LoginState()
    object Empty: LoginState()
}