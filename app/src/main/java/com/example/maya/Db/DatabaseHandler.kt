package com.example.maya.Db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.maya.Bl.Cart
import com.example.maya.Bl.Product
import com.example.maya.Ui.Libs.Firebase
import com.example.maya.Ui.Models.*

private val DATABASE_NAME = "MAYA_DB"
private val DB_Version = 17

class DatabaseHandler(val context:Context): DatabaseHandlerInterface, SQLiteOpenHelper(context, DATABASE_NAME, null, DB_Version)  {

    private val LANDSCAPE_TABLE_NAME  = "Carousel"
    private val PRODUCT_TABLE_NAME  = "Product"
    private val CART_TABLE_NAME = "Cart"
    private val ORDER_TABLE_NAME = "Orders"
    private val USER_TABLE_NAME = "Users"

    private val COL_DESC = "description"
    private val COL_PRICE = "price"
    private val COL_IMAGE = "image"
    private val COL_ID = "id"
    private val COL_USER_ID = "userid"
    private val COL_EMAIL = "email"
    private val COL_ORDER_ID = "orderid"
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
            COL_ID + " INTEGER," +
            COL_USER_ID + " TEXT," +
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

    private val createOrdersTable = "CREATE TABLE " + ORDER_TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_ORDER_ID + " TEXT," +
            COL_USER_ID + " TEXT," +
            COL_NAME + " TEXT," +
            COL_QUANTITY + " INTEGER," +
            COL_PRICE + " INTEGER," +
            COL_IMAGE + " INTEGER)";

    private val createUserTable = "CREATE TABLE " + USER_TABLE_NAME + " (" +
            COL_USER_ID + " TEXT," +
            COL_NAME + " TEXT," +
            COL_EMAIL + " TEXT)";

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createLanscapeTable)
        db?.execSQL(createProductTable)
        db?.execSQL(createCartTable)
        db?.execSQL(createOrdersTable)
        db?.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Carousel");
        db?.execSQL("DROP TABLE IF EXISTS Product");
        db?.execSQL("DROP TABLE IF EXISTS Cart");
        db?.execSQL("DROP TABLE IF EXISTS Orders");
        db?.execSQL("DROP TABLE IF EXISTS Users");
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
            }while (result.moveToNext())
        }

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

    @SuppressLint("Range")
    override fun readSuggestedProducts(cat: String, iid: String): MutableList<ProductModel> {
        val list: MutableList<ProductModel> = arrayListOf()
        val db = this.readableDatabase
        val query = "Select * from " + PRODUCT_TABLE_NAME + " where " + COL_CATEGORY + " = " + "'"+ cat + "'"
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

                if (iid != id){
                    val item = ProductModel(name, id, price, desc, rating.toFloat(), discount, have, brand, image, category, note)
                    list.add(item)
                }

            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    @SuppressLint("Range")
    override fun readAllProducts(): MutableList<ProductModel> {
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

                val item = ProductModel(name, id, price, desc, rating.toFloat(), discount, have, brand, image, category, note)
                list.add(item)

            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    override fun deleteProduct(id: String) {
        val db = this.writableDatabase
        db.delete(PRODUCT_TABLE_NAME, COL_ID + "= '" + id + "'", null)
        db.close()
    }

    @SuppressLint("Range")
    override fun searchProduct(cat: String) : MutableList<ProductModel> {
        val list: MutableList<ProductModel> = arrayListOf()
        val db = this.readableDatabase
        val query = "Select * from " + PRODUCT_TABLE_NAME + " where " + COL_CATEGORY + " like " + "'%"+ cat + "%'"
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

                val item = ProductModel(name, id, price, desc, rating.toFloat(), discount, have, brand, image, category, note)
                list.add(item)

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
        cv.put(COL_USER_ID, Firebase.firebaseAuth.currentUser!!.uid)
        cv.put(COL_NAME, c.productName)
        cv.put(COL_PRICE, c.productPrice)
        cv.put(COL_IMAGE, c.productImage)
        cv.put(COL_QUANTITY, c.productQuantity)

        val Query =
            "Select * from " + CART_TABLE_NAME + " where " + COL_ID + " = " + c.productId +" AND " + COL_USER_ID + " = " + "'" + Firebase.firebaseAuth.currentUser!!.uid + "'"

        val verifyResult = db.rawQuery(Query, null)

        if (verifyResult.count <= 0){
            var result = db.insert(CART_TABLE_NAME, null, cv)

            if (result == -1.toLong() )
                Toast.makeText(context, "Cart Product Insertion Failed", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "Cart Product Inserted Successfully", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Cart Product Already Added!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("Range")
    override fun readCartProducts(): MutableList<CartModel> {
        val list: MutableList<CartModel> = arrayListOf()
        val db = this.readableDatabase
        val Query =
            "Select * from " + CART_TABLE_NAME + " where " + COL_USER_ID + " = " + "'" + Firebase.firebaseAuth.currentUser!!.uid + "'"
        val result = db.rawQuery(Query, null)
//        println(userId)

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
        db.update(CART_TABLE_NAME, cv, COL_ID + "= '" + id + "' AND " + COL_USER_ID + "= '" + Firebase.firebaseAuth.currentUser!!.uid + "'", null)
        db.close()
    }

    override fun deleteCartData(id: String) {
        val db = this.writableDatabase
        db.delete(CART_TABLE_NAME, COL_ID + "= '" + id + "' AND " + COL_USER_ID + "= '" + Firebase.firebaseAuth.currentUser!!.uid + "'", null)
        db.close()
    }

    override fun deleteUserCart() {
        val db = this.writableDatabase
        db.delete(CART_TABLE_NAME,  COL_USER_ID + "= '" + Firebase.firebaseAuth.currentUser!!.uid + "'", null)
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

    override fun insertProductOrder(o: OrderModel) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ORDER_ID, o.order_id)
        cv.put(COL_USER_ID, Firebase.firebaseAuth.currentUser!!.uid)
        cv.put(COL_NAME, o.order_name)
        cv.put(COL_PRICE, o.order_price)
        cv.put(COL_IMAGE, o.order_image)
        cv.put(COL_QUANTITY, o.order_quantity)

            var result = db.insert(ORDER_TABLE_NAME, null, cv)

            if (result == -1.toLong() )
                Toast.makeText(context, "Products Order Insertion Failed", Toast.LENGTH_SHORT).show()

        deleteUserCart()

    }

    @SuppressLint("Range")
    override fun readProductOrders(): MutableList<OrdersModel> {
        val list: MutableList<OrdersModel> = arrayListOf()
        val db = this.readableDatabase
        val Query =
            "Select * from " + ORDER_TABLE_NAME + " where " + COL_USER_ID + " = " + "'" + Firebase.firebaseAuth.currentUser!!.uid + "'"
        val result = db.rawQuery(Query, null)

        var temp: MutableList<String> = arrayListOf()

        if (result.moveToFirst()){
            do{
                val order_id = result.getString(result.getColumnIndex(COL_ORDER_ID))

                if(!(order_id in temp)){
                    val item = OrdersModel(orderNumber = order_id)
                    list.add(item)
                }

                temp.add(order_id)


            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    @SuppressLint("Range")
    override fun readProductOrder(orderId: String): MutableList<OrderModel> {
        val list: MutableList<OrderModel> = arrayListOf()
        val db = this.readableDatabase
        val Query =
            "Select * from " + ORDER_TABLE_NAME + " where " + COL_ORDER_ID + "= '" + orderId + "' AND " + COL_USER_ID + " = " + "'" + Firebase.firebaseAuth.currentUser!!.uid + "'"
        val result = db.rawQuery(Query, null)

        if (result.moveToFirst()){
            do{
                val name = result.getString(result.getColumnIndex(COL_NAME))
                val price = result.getString(result.getColumnIndex(COL_PRICE)).toInt()
                val image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
                val quantity = result.getString(result.getColumnIndex(COL_QUANTITY)).toInt()

                val item = OrderModel(name, orderId, quantity, price, image)
                list.add(item)


            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    override fun getOrderTotal(orderId: String): Int {
        val data = readProductOrder(orderId)
        var total = 0
        for(i in 0..(data.size-1)){
            total += data.get(i).order_price * data.get(i).order_quantity
        }

        return total
    }

    override fun insertUser(u: UserModel) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USER_ID, u.userID)
        cv.put(COL_NAME, u.userName)
        cv.put(COL_EMAIL, u.userEmail)

        var result = db.insert(USER_TABLE_NAME, null, cv)

        if (result == -1.toLong() )
            Toast.makeText(context, "User Registeration Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    override fun readUser(): MutableList<UserModel> {
        var list: MutableList<UserModel> = arrayListOf()
        val db = this.readableDatabase
        val query = "Select * from " + USER_TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do{
                val uid = result.getString(result.getColumnIndex(COL_USER_ID))
                val name = result.getString(result.getColumnIndex(COL_NAME))
                val email = result.getString(result.getColumnIndex(COL_EMAIL))

                list.add(UserModel(name, uid, email))
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }
}