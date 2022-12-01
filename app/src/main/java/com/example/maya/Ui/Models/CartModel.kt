package com.example.maya.Ui.Models

import androidx.lifecycle.ViewModel
import java.io.Serializable

data class CartModel(
    val productId:String = "",
    val productName:String = "",
    val productPrice:String = "",
    val productImage:Int = 0,
    val productQuantity:Int = 1,
): ViewModel(){
}

