package com.yusuf.e_commerceapp.hilt.service.auth

import com.yusuf.e_commerceapp.model.network.response.ConfirmationResponse

sealed class COnfirmationState {
    class Success(val response: ConfirmationResponse): COnfirmationState()
    class Failure(val error: String): COnfirmationState()
    object Empty: COnfirmationState()
}