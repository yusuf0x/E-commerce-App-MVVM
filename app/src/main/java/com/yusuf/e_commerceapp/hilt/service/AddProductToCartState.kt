package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.model.network.response.AddProducttoCartResponse
import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import com.yusuf.e_commerceapp.model.network.response.GetCartitemsResponse

sealed class AddProductToCartState {
    class Success(val response: AddProducttoCartResponse): AddProductToCartState()
    class Failure(val error: String): AddProductToCartState()
    object Empty: AddProductToCartState()
}