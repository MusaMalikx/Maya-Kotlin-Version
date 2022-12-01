package com.example.maya.Bl

import android.content.Context
import com.example.maya.Db.DatabaseHandler
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

}