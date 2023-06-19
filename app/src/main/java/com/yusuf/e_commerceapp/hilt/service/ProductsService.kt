package com.yusuf.e_commerceapp.hilt.service

import com.yusuf.e_commerceapp.model.network.SupportBody
import com.yusuf.e_commerceapp.model.network.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ProductsService {

    @GET("getcategorie")
    suspend fun getAllCategories(): GetAllCategoryResponse

    @GET("getproducts")
    suspend fun getAllProducts():GetAllProductsResponse

    @POST("support")
    suspend fun sendSupport(
        @Body supportBody: SupportBody
    ):SupportResponse

//    @Multipart
    @POST("update/{id}")
    suspend fun updateProfile(
        @Path("id") id:Int,
        @Body params : RequestBody
    ):UpdateProfileResponse

    @FormUrlEncoded
    @POST("getchartitems")
    suspend fun getCartsById(
        @Field("iduser") iduser:Int
    ): GetCartitemsResponse

    @FormUrlEncoded
    @POST("addproducttocart")
    suspend fun addProducttoCart(
        @Field("iduser") iduser:Int,
        @Field("idproduct") idproduct:Int
    ):AddProducttoCartResponse

}