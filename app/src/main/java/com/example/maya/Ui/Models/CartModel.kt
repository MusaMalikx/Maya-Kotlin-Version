package com.example.maya.Ui.Models

import androidx.lifecycle.ViewModel
import java.io.Serializable

data class CartModel(
    val productName:String = "",
    val productId:String = "",
    val productPrice:String = "",
    val productDes:String = "",
    val productRating: Float = 0.0F,
    val productDisCount:String = "",
    val productHave:Boolean = false,
    val productBrand:String = "",
    val productImage:Int = 0,
    val productCategory:String = "",
    val productNote:String = "",
    val productQuantity:Int = 1,
): ViewModel(){
}

