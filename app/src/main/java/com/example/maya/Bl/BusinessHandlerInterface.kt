package com.example.maya.Bl

import android.graphics.Bitmap
import com.example.maya.Ui.Models.*

interface BusinessHandlerInterface {
    fun insertLandscapePic(img: Int)
    fun readLandscapePics(): MutableList<Int>
    fun insertProduct(p: Product)
    fun readNewProducts(): MutableList<ProductModel>
    fun readSaleProducts(): MutableList<ProductModel>
    fun readSuggestedProducts(cat: String, id: String) : MutableList<ProductModel>
    fun readAllProducts() : MutableList<ProductModel>
    fun deleteProduct(id: String)
    fun insertCartProduct(c: Cart)
    fun readCartProducts() : MutableList<CartModel>
    fun updateCartQuantityProduct(id: String, quantity: Int)
    fun deleteCartData(id: String)
    fun getCartTotal():Int
    fun insertProductOrder(o: OrderModel)
    fun readProductOrders() : MutableList<OrdersModel>
    fun readProductOrder(orderId: String) : MutableList<OrderModel>
    fun getOrderTotal(orderId: String):Int
    fun insertUser(user: UserModel)
    fun readUser() : MutableList<UserModel>
}