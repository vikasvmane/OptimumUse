package com.vikas.optimumuse.model.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vikas.optimumuse.model.Product

@Dao
interface ProductDAO {
    @Query("SELECT * FROM Product")
    suspend fun getAll(): List<Product>

    @Insert
    suspend fun insertAll(vararg product: Product)

    @Delete
    suspend fun delete(product: Product)
}