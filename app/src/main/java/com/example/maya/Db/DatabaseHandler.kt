package com.example.maya.Db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.maya.Bl.Cart
import com.example.maya.Bl.Product
import com.example.maya.Ui.Models.CartModel
import com.example.maya.Ui.Models.ProductModel

private val DATABASE_NAME = "MAYA_DB"
private val DB_Version = 12

class DatabaseHandler(val context:Context): DatabaseHandlerInterface, SQLiteOpenHelper(context, DATABASE_NAME, null, DB_Version)  {

    private val LANDSCAPE_TABLE_NAME  = "Carousel"
    private val PRODUCT_TABLE_NAME  = "Product"
    private val CART_TABLE_NAME = "Cart"

    private val COL_DESC = "description"
    private val COL_PRICE = "price"
    private val COL_IMAGE = "image"
    private val COL_ID = "id"
    private val COL_QUANTITY = "quant"
    private val COL_NAME = "name"
    private val COL_RATING = "rating"
    private val COL_DISCOUNT = "discount"
    private val COL_HAVE = "have"
    private val COL_BRAND = "brand"
    private val COL_CATEGORY = "category"
    private val COL_NOTE = "note"

    private val createLanscapeTable = "CREATE TABLE " + LANDSCAPE_TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_IMAGE + " INTEGER)";

    private val createCartTable = "CREATE TABLE " + CART_TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY," +
            COL_NAME + " TEXT," +
            COL_QUANTITY + " INTEGER," +
            COL_PRICE + " INTEGER," +
            COL_IMAGE + " INTEGER)";

    private val createProductTable = "CREATE TABLE " + PRODUCT_TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT," +
            COL_DESC + " TEXT," +
            COL_DISCOUNT + " TEXT," +
            COL_HAVE + " INTEGER," +
            COL_BRAND + " TEXT," +
            COL_CATEGORY + " TEXT," +
            COL_NOTE + " TEXT," +
            COL_PRICE + " INTEGER," +
            COL_RATING + " TEXT," +
            COL_IMAGE + " INTEGER)";

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createLanscapeTable)
        db?.execSQL(createProductTable)
        db?.execSQL(createCartTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Carousel");
        db?.execSQL("DROP TABLE IF EXISTS Product");
        db?.execSQL("DROP TABLE IF EXISTS Cart");
        onCreate(db);
    }

    override fun insertLandscapePic(img: Int) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_IMAGE, img)
        var result = db.insert(LANDSCAPE_TABLE_NAME, null, cv)

        if (result == -1.toLong() )
            Toast.makeText(context, "Failed to Insert Landscape Images", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Landscape Images successfully inserted", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    override fun readLandscapePics():MutableList<Int> {
        var list: MutableList<Int> = arrayListOf()
        val db = this.readableDatabase
        val query = "Select * from " + LANDSCAPE_TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do{
                val image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
                list.add(image)
                println(image)
            }while (result.moveToNext())
        }

        if (list != null )
            Toast.makeText(context, "Images read successfully", Toast.LENGTH_SHORT).show()

        result.close()
        db.close()
        return list
    }

    override fun insertProduct(p: Product) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, p.productName)
        cv.put(COL_DESC, p.productDes)
        cv.put(COL_DISCOUNT, p.productDisCount)
        cv.put(COL_HAVE, p.productHave)
        cv.put(COL_CATEGORY, p.productCategory)
        cv.put(COL_NOTE, p.productNote)
        cv.put(COL_RATING, p.productRating.toString())
        cv.put(COL_PRICE, p.productPrice)
        cv.put(COL_IMAGE, p.productImage)
        cv.put(COL_BRAND, p.productBrand)

        var result = db.insert(PRODUCT_TABLE_NAME, null, cv)

        if (result == -1.toLong() )
            Toast.makeText(context, "Product Insertion Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Product Inserted Successfully", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    override fun readProducts(state: String): MutableList<ProductModel> {
        val list: MutableList<ProductModel> = arrayListOf()
        val db = this.readableDatabase
        val query = "Select * from " + PRODUCT_TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do{
                val id = result.getString(result.getColumnIndex(COL_ID))
                val name = result.getString(result.getColumnIndex(COL_NAME))
                val desc = result.getString(result.getColumnIndex(COL_DESC))
                val discount = result.getString(result.getColumnIndex(COL_DISCOUNT))
                val bool = result.getString(result.getColumnIndex(COL_HAVE)).toInt()
                val category = result.getString(result.getColumnIndex(COL_CATEGORY))
                val note = result.getString(result.getColumnIndex(COL_NOTE))
                val rating = result.getString(result.getColumnIndex(COL_RATING))
                val price = result.getString(result.getColumnIndex(COL_PRICE))
                val image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
                val brand = result.getString(result.getColumnIndex(COL_BRAND))

                var have = false
                if(bool == 1){
                    have = true
                }

                if (state == "newProducts" && discount.toInt() == 0){
                    val item = ProductModel(name, id, price, desc, rating.toFloat(), discount, have, brand, image, category, note)
                    list.add(item)
                }
                else if (state == "saleProducts" && discount.toInt() != 0){
                    val item = ProductModel(name, id, price, desc, rating.toFloat(), discount, have, brand, image, category, note)
                    list.add(item)
                }

            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    override fun insertCartProduct(c: Cart) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID, c.productId)
        cv.put(COL_NAME, c.productName)
        cv.put(COL_PRICE, c.productPrice)
        cv.put(COL_IMAGE, c.productImage)
        cv.put(COL_QUANTITY, c.productQuantity)

        var result = db.insert(CART_TABLE_NAME, null, cv)

        if (result == -1.toLong() )
            Toast.makeText(context, "Cart Product Insertion Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Cart Product Inserted Successfully", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    override fun readCartProducts(): MutableList<CartModel> {
        val list: MutableList<CartModel> = arrayListOf()
        val db = this.readableDatabase
        val query = "Select * from " + CART_TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do{
                val id = result.getString(result.getColumnIndex(COL_ID))
                val name = result.getString(result.getColumnIndex(COL_NAME))
                val price = result.getString(result.getColumnIndex(COL_PRICE))
                val image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
                val quantity = result.getString(result.getColumnIndex(COL_QUANTITY)).toInt()

                val item = CartModel(id, name,price,image, quantity)
                list.add(item)

            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    @SuppressLint("Range")
    override fun updateCartQuantityProduct(id: String, quantity: Int) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_QUANTITY, quantity)
        db.update(CART_TABLE_NAME, cv, COL_ID + "=?", arrayOf(id))
        Toast.makeText(context, "Cart Updated", Toast.LENGTH_SHORT).show()
        db.close()
    }

    override fun deleteCartData(id: String) {
        val db = this.writableDatabase
        db.delete(CART_TABLE_NAME, COL_ID + "=?", arrayOf(id) )
        Toast.makeText(context, "Cart Item Removed!", Toast.LENGTH_SHORT).show()
        db.close()
    }

    override fun getCartTotal(): Int {
        val data = readCartProducts()
        var total = 0
        for(i in 0..(data.size-1)){
            total += data.get(i).productPrice.toInt() * data.get(i).productQuantity
        }

        return total
    }
}