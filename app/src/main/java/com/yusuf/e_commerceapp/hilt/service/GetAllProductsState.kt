package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.model.network.response.GetAllProductsResponse

sealed class GetAllProductsState {
    class Success(val response: GetAllProductsResponse): GetAllProductsState()
    class Failure(val error: String): GetAllProductsState()
    object Empty: GetAllProductsState()
}