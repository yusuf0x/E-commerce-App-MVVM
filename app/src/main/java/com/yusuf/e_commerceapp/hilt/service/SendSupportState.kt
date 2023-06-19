package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import com.yusuf.e_commerceapp.model.network.response.GetCartitemsResponse
import com.yusuf.e_commerceapp.model.network.response.SupportResponse

sealed class SendSupportState {
    class Success(val response: SupportResponse): SendSupportState()
    class Failure(val error: String): SendSupportState()
    object Empty: SendSupportState()
}