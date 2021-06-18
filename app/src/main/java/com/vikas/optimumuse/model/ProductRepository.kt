package com.vikas.optimumuse.model

import androidx.lifecycle.LiveData

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun deleteProduct(product: Product)
    suspend fun insertProduct(product: Product)
}