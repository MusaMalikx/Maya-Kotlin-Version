package com.example.maya.Db

import com.example.maya.Bl.Product
import com.example.maya.Ui.Models.ProductModel

interface DatabaseHandlerInterface {
    fun insertLandscapePic(img: Int)
    fun readLandscapePics() : MutableList<Int>
    fun insertProduct(p: Product)
    fun readProducts(state: String) : MutableList<ProductModel>
}