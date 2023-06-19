package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse

sealed class GetAllCategoriesState {
    class Success(val response: GetAllCategoryResponse): GetAllCategoriesState()
    class Failure(val error: String): GetAllCategoriesState()
    object Empty: GetAllCategoriesState()
}