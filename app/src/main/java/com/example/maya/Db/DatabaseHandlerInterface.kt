package com.example.maya.Db

import android.graphics.Bitmap
import com.example.maya.Bl.Cart
import com.example.maya.Bl.Product
import com.example.maya.Ui.Models.CartModel
import com.example.maya.Ui.Models.OrderModel
import com.example.maya.Ui.Models.OrdersModel
import com.example.maya.Ui.Models.ProductModel

interface DatabaseHandlerInterface {
    fun insertLandscapePic(img: Int)
    fun readLandscapePics() : MutableList<Int>
    fun insertProduct(p: Product)
    fun readProducts(state: String) : MutableList<ProductModel>
    fun insertCartProduct(c: Cart)
    fun readCartProducts() : MutableList<CartModel>
    fun updateCartQuantityProduct(id: String, quantity: Int)
    fun deleteCartData(id: String)
    fun getCartTotal():Int
    fun insertProductOrder(o: OrdersModel)
    fun readProductOrders() : MutableList<OrdersModel>
}