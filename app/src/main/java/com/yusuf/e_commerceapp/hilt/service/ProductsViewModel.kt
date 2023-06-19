package com.yusuf.e_commerceapp.hilt.service

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.e_commerceapp.core.Resource
import com.yusuf.e_commerceapp.hilt.service.auth.LoginState
import com.yusuf.e_commerceapp.model.network.SupportBody
import com.yusuf.e_commerceapp.model.network.response.AddProducttoCartResponse
import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
): ViewModel() {
    private val _allCategoriesState = MutableStateFlow<GetAllCategoriesState>(GetAllCategoriesState.Empty)
    val allCategoriesState = _allCategoriesState.asStateFlow()

    private val _allProductsState = MutableStateFlow<GetAllProductsState>(GetAllProductsState.Empty)
    val allProductsState = _allProductsState.asStateFlow()

    private val _allCartsState = MutableStateFlow<GetAllCartsState>(GetAllCartsState.Empty)
    val allCartsState = _allCartsState.asStateFlow()

    private val _sendSupportState = MutableStateFlow<SendSupportState>(SendSupportState.Empty)
    val sendSupportState = _sendSupportState.asStateFlow()

    private val _updateProfileState = MutableStateFlow<UpdateProfileState>(UpdateProfileState.Empty)
    val updateProfileState = _updateProfileState.asStateFlow()

    private val _addProductToCartState = MutableStateFlow<AddProductToCartState>(AddProductToCartState.Empty)
    val addProductToCartState = _addProductToCartState.asStateFlow()

//    var nom = MutableLiveData<String>()
//    var email = MutableLiveData<String>()
//    var message = MutableLiveData<String>()

    fun getAllCategories(){
        viewModelScope.launch {
            when(val response = productsRepository.getAllCategories()){
                is Resource.Success  -> _allCategoriesState.value = GetAllCategoriesState.Success(response.data!!)
                is Resource.Error -> _allCategoriesState.value =
                    GetAllCategoriesState.Failure(
                        response.error ?: "Unknown error."
                    )
                else -> Unit
            }
        }
    }
    fun getAllProducts(){
        viewModelScope.launch {
            when(val response = productsRepository.getAllProducts()){
                is Resource.Success  -> _allProductsState.value = GetAllProductsState.Success(response.data!!)
                is Resource.Error -> _allProductsState.value =
                    GetAllProductsState.Failure(
                        response.error ?: "Unknown error."
                    )
                else -> Unit
            }
        }
    }

    fun getAllCarts(id:Int){
        viewModelScope.launch {
            when(val response = productsRepository.getAllCarts(id)){
                is Resource.Success  -> _allCartsState.value = GetAllCartsState.Success(response.data!!)
                is Resource.Error -> _allCartsState.value =
                    GetAllCartsState.Failure(
                        response.error ?: "Unknown error."
                    )
                else -> Unit
            }
        }
    }
    fun sendSupport(nom:String,email:String,message:String){
        viewModelScope.launch {
            when(val response= productsRepository.sendSupport(SupportBody(nom,email,message))){
                is Resource.Success -> _sendSupportState.value = SendSupportState.Success(response.data!!)
                is Resource.Error -> _sendSupportState.value = SendSupportState.Failure(
                    response.error ?: "Unknown error."
                )
                else -> Unit
            }
        }
    }
    fun updateProfile(
        id: Int,
        body: RequestBody
    ){
        viewModelScope.launch {
            when(val response= productsRepository.updateProfile(id,body)){
                is Resource.Success -> _updateProfileState.value = UpdateProfileState.Success(response.data!!)
                is Resource.Error -> _updateProfileState.value = UpdateProfileState.Failure(
                    response.error ?: "Unknown error."
                )
                else -> Unit
            }
        }
    }
     fun addProductToCart(
        id:Int,
        idproduct: Int
    ) {
        viewModelScope.launch {
            when(val response= productsRepository.addProductToCart(id,idproduct)){
                is Resource.Success -> _addProductToCartState.value = AddProductToCartState.Success(response.data!!)
                is Resource.Error -> _addProductToCartState.value = AddProductToCartState.Failure(
                    response.error ?: "Unknown error."
                )
                else -> Unit
            }
        }
    }

}