package com.yusuf.e_commerceapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

  @Query("SELECT * FROM product")
  fun getAll() : Flow<List<Product>>

  @Query("SELECT * FROM product WHERE user_id= :userId")
  fun loadAllByUserId(userId: Int): Flow<List<Product>>

  @Insert
  fun insert(product: Product)

  @Delete
  fun delete(product: Product)
}