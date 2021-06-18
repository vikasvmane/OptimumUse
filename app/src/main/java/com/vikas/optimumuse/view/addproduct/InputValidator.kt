package com.vikas.optimumuse.view.addproduct

object InputValidator {
    fun isInputValid(productName: String, expiryDate: String) =
        productName.isNotEmpty() && expiryDate.isNotEmpty()
}