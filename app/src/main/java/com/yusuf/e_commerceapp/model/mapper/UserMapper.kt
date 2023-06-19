package com.yusuf.e_commerceapp.model.mapper

import com.yusuf.e_commerceapp.model.domain.User
import com.yusuf.e_commerceapp.model.network.response.LoginResponse

object UserMapper {
    fun buildFrom(
        loginResponse: LoginResponse
    ):User{
        return User(
            id = loginResponse.id,
            firstname = loginResponse.firstname,
            lastname = loginResponse.lastname,
            email = loginResponse.email,
            password = loginResponse.password,
            role = loginResponse.role,
            birthday = loginResponse.birthday,
            image = loginResponse.image,
            remember_token = loginResponse.remember_token,
            updated_at = loginResponse.updated_at,
            created_at = loginResponse.created_at
        )
    }
}