package com.vikas.optimumuse.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vikas.optimumuse.model.ProductRepository
import com.vikas.optimumuse.model.ProductRepositoryImpl
import com.vikas.optimumuse.model.db.AppDatabase

class MyViewModelFactory(private val context: Context?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = ProductRepositoryImpl(AppDatabase.getInstance(context!!).productDao())
        return modelClass.getConstructor(ProductRepository::class.java).newInstance(repository)
    }
}