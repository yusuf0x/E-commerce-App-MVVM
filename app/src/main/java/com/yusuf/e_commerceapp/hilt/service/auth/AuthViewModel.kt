package com.yusuf.e_commerceapp.hilt.service.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.e_commerceapp.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
):ViewModel(){

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Empty)
    val registerState = _registerState.asStateFlow()

    private val _confirmationState = MutableStateFlow<COnfirmationState>(COnfirmationState.Empty)
    val confirmationState = _confirmationState.asStateFlow()


    fun login(email: String,password: String){
        viewModelScope.launch {
            when(val response = authRepository.login(email=email,password=password)){
                is Resource.Success  -> _loginState.value = LoginState.Success(response.data!!)
                is Resource.Error -> _loginState.value =
                    LoginState.Failure(
                        response.error ?: "Unknown error."
                    )
                else -> Unit
            }
        }
    }

    fun register(email: String,password: String,nom:String,prenom:String){
        viewModelScope.launch {
            when(val response = authRepository.register(email=email,password=password,nom=nom,prenom=prenom)){
                is Resource.Success  -> _registerState.value = RegisterState.Success(response.data!!)
                is Resource.Error -> _registerState.value =
                    RegisterState.Failure(
                        response.error ?: "Unknown error."
                    )
                else -> Unit
            }
        }
//    = viewModelScope.launch {

//        val response : Response<RegisterResponse> = authRepository.register(email=email,password=password,nom=nom,prenom=prenom)
//        if(response.isSuccessful){
//
//        }else{
//
//        }
    }

    fun confirmer(email: String,password: String,nom:String,prenom:String){
        viewModelScope.launch {
            when(val response = authRepository.confimer(email=email,password=password,nom=nom,prenom=prenom)){
                is Resource.Success  -> _confirmationState.value = COnfirmationState.Success(response.data!!)
                is Resource.Error -> _confirmationState.value =
                    COnfirmationState.Failure(
                        response.error ?: "Unknown error."
                    )
                else -> Unit
            }
        }
//    = viewModelScope.launch {
//        val response : Response<ConfirmationResponse> = authRepository.confimer(email=email,password=password,nom=nom,prenom=prenom)
//        if(response.isSuccessful){
//
//        }else{
//
//        }
    }
}