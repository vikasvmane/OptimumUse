package com.vikas.optimumuse.view.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.optimumuse.model.Product
import com.vikas.optimumuse.model.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(private val productRepository: ProductRepository) : ViewModel() {
    var productList = MutableLiveData<List<Product>>()

    fun getAllProducts() {
        viewModelScope.launch {
            productList.value = productRepository.getProducts()
        }
    }
}