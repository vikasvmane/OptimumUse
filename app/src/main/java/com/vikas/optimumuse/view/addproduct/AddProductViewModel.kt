package com.vikas.optimumuse.view.addproduct

import androidx.lifecycle.ViewModel
import com.vikas.optimumuse.model.Product
import com.vikas.optimumuse.model.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class AddProductViewModel(private val productRepository: ProductRepository): ViewModel() {
    fun insertProduct(product: Product){
        CoroutineScope(Main).launch {
            productRepository.insertProduct(product)
        }
    }
}