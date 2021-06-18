package com.vikas.optimumuse.model

import androidx.lifecycle.LiveData
import com.vikas.optimumuse.model.db.ProductDAO
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(private val appDatabase: ProductDAO) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return withContext(IO) {
            appDatabase.getAll()
        }
    }

    override suspend fun deleteProduct(product: Product) {
        appDatabase.delete(product)
    }

    override suspend fun insertProduct(product: Product) {
        appDatabase.insertAll(product)
    }
}