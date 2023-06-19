package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.core.Resource
import com.yusuf.e_commerceapp.model.network.LoginPostBody
import com.yusuf.e_commerceapp.model.network.SupportBody
import com.yusuf.e_commerceapp.model.network.response.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import retrofit2.http.Part
import java.io.IOException
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsService: ProductsService
) {
    suspend fun getAllCategories(): Resource<GetAllCategoryResponse> {
        return try {
            val response = productsService.getAllCategories()
            Resource.Success(response)
        }catch (e: HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
    }
    suspend fun getAllProducts():Resource<GetAllProductsResponse>{
        return try {
            val  response = productsService.getAllProducts()
            Resource.Success(response)
        }catch (e: HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
    }
    suspend fun getAllCarts(id:Int):Resource<GetCartitemsResponse>{
        return try {
            val  response = productsService.getCartsById(id)
            Resource.Success(response)
        }catch (e: HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
    }
    suspend fun sendSupport(supportBody: SupportBody):Resource<SupportResponse>{
        return try {
            val  response = productsService.sendSupport(supportBody)
            Resource.Success(response)
        }catch (e: HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
    }
    suspend fun updateProfile(
        id:Int,
        body: RequestBody
    ) :Resource<UpdateProfileResponse>{
        return try {
            val  response = productsService.updateProfile(id,body)
            Resource.Success(response)
        }catch (e: HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
    }
    suspend fun addProductToCart(
        id:Int,
        idproduct: Int
    ) :Resource<AddProducttoCartResponse>{
        return try {
            val  response = productsService.addProducttoCart(id,idproduct)
            Resource.Success(response)
        }catch (e: HttpException){
            Resource.Error(error = e.message ?: "Unknown error.")
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: "Unknown error.")
        }
    }
}