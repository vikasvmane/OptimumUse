package com.vikas.optimumuse.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vikas.optimumuse.model.ProductRepository

class MyViewModelFactory(private val repository: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProductRepository::class.java).newInstance(repository)
    }
}