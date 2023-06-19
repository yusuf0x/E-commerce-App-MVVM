package com.yusuf.e_commerceapp.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDbViewModel @Inject constructor(
    val productDao: ProductDao
) : ViewModel() {
    fun allProducts(): Flow<List<Product>> = productDao.getAll()
    fun allProductsById(id: Int): Flow<List<Product>> = productDao.loadAllByUserId(id)
    fun insert(product: Product) = viewModelScope.launch {
        productDao.insert(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        productDao.delete(product)
    }


}