package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import com.yusuf.e_commerceapp.model.network.response.GetCartitemsResponse

sealed class GetAllCartsState {
    class Success(val response: GetCartitemsResponse): GetAllCartsState()
    class Failure(val error: String): GetAllCartsState()
    object Empty: GetAllCartsState()
}