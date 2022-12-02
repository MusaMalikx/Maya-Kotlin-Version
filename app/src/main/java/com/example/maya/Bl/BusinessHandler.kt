package com.example.maya.Bl

import android.content.Context
import android.graphics.Bitmap
import com.example.maya.Db.DatabaseHandler
import com.example.maya.Ui.Models.CartModel
import com.example.maya.Ui.Models.ProductModel

class BusinessHandler(val context: Context):BusinessHandlerInterface {

    private val db = DatabaseHandler(context)

    override fun insertLandscapePic(img: Int) {
        db.insertLandscapePic(img)
    }

    override fun readLandscapePics(): MutableList<Int> {
        return db.readLandscapePics()
    }

    override fun insertProduct(p: Product) {
        db.insertProduct(p)
    }

    override fun readNewProducts(): MutableList<ProductModel> {
        return db.readProducts("newProducts")
    }

    override fun readSaleProducts(): MutableList<ProductModel> {
        return db.readProducts("saleProducts")
    }

    override fun insertCartProduct(c: Cart) {
        db.insertCartProduct(c)
    }

    override fun readCartProducts(): MutableList<CartModel> {
        return db.readCartProducts()
    }

    override fun updateCartQuantityProduct(id: String, quantity: Int) {
        db.updateCartQuantityProduct(id, quantity)
    }

    override fun deleteCartData(id: String) {
        db.deleteCartData(id)
    }

    override fun getCartTotal(): Int {
        return db.getCartTotal()
    }

}