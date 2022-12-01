package com.example.maya.Bl

import com.example.maya.Ui.Models.ProductModel

interface BusinessHandlerInterface {
    fun insertLandscapePic(img: Int)
    fun readLandscapePics(): MutableList<Int>
    fun insertProduct(p: Product)
    fun readNewProducts(): MutableList<ProductModel>
    fun readSaleProducts(): MutableList<ProductModel>
}