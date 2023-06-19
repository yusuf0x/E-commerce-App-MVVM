package com.yusuf.e_commerceapp.hilt.service.auth

import com.yusuf.e_commerceapp.core.Resource
import com.yusuf.e_commerceapp.model.network.ConfirmationPostBody
import com.yusuf.e_commerceapp.model.network.LoginPostBody
import com.yusuf.e_commerceapp.model.network.RegisterPostBody
import com.yusuf.e_commerceapp.model.network.response.ConfirmationResponse
import com.yusuf.e_commerceapp.model.network.response.LoginResponse
import com.yusuf.e_commerceapp.model.network.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    suspend fun login(
        @Body postBody: LoginPostBody
    ): LoginResponse

    @POST("register")
    suspend fun register(
        @Body postBody: RegisterPostBody
    ):RegisterResponse

    @POST("confimer")
    suspend fun confimer(
        @Body postBody: ConfirmationPostBody
    ):ConfirmationResponse

}