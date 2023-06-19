package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import com.yusuf.e_commerceapp.model.network.response.GetCartitemsResponse
import com.yusuf.e_commerceapp.model.network.response.UpdateProfileResponse

sealed class UpdateProfileState {
    class Success(val response: UpdateProfileResponse): UpdateProfileState()
    class Failure(val error: String): UpdateProfileState()
    object Empty: UpdateProfileState()
}