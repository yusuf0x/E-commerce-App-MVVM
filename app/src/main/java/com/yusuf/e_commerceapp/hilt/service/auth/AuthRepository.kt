package com.yusuf.e_commerceapp.hilt.service.auth

import com.yusuf.e_commerceapp.core.Resource
import com.yusuf.e_commerceapp.model.network.ConfirmationPostBody
import com.yusuf.e_commerceapp.model.network.LoginPostBody
import com.yusuf.e_commerceapp.model.network.RegisterPostBody
import com.yusuf.e_commerceapp.model.network.response.ConfirmationResponse
import com.yusuf.e_commerceapp.model.network.response.LoginResponse
import com.yusuf.e_commerceapp.model.network.response.RegisterResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepository  @Inject constructor(private val authService: AuthService) {

    suspend fun login(email: String,password: String): Resource<LoginResponse> {
        return try {
            val response = authService.login(LoginPostBody(email = email,password = password))
            Resource.Success<LoginResponse>(response)
        }catch (e:HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
//        return authService.login(LoginPostBody(email = email,password = password))
    }
    suspend fun register(email: String,password: String,nom:String,prenom:String):Resource<RegisterResponse> {
        return try {
            val response = authService.register(RegisterPostBody(email = email,password = password, nom = nom , prenom = prenom))
            Resource.Success<RegisterResponse>(response)
        }catch (e:HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
//        return authService.register(RegisterPostBody(email = email,password = password, nom = nom , prenom = prenom))
    }

    suspend fun confimer(email: String,password: String,nom:String,prenom:String):Resource<ConfirmationResponse> {
        return try {
            val response = authService.confimer(ConfirmationPostBody(email = email,password = password, nom = nom , prenom = prenom))
            Resource.Success<ConfirmationResponse>(response)
        }catch (e:HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
//        return authService.confimer(ConfirmationPostBody(email = email,password = password, nom = nom , prenom = prenom))
    }




}