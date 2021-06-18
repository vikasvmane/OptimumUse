package com.vikas.optimumuse.model

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun deleteProduct(product: Product)
    suspend fun insertProduct(product: Product)
}