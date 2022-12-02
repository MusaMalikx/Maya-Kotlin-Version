package com.example.maya.Ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.Bl.Cart
import com.example.maya.R
import com.example.maya.Ui.Adapters.ProductAdapter
import com.example.maya.Ui.Libs.Firebase
import com.example.maya.Ui.Models.ProductModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductActivity: AppCompatActivity(), View.OnClickListener {

    lateinit var recommendProduct:MutableList<ProductModel>
    lateinit var recommendProductAdapter: ProductAdapter
    lateinit var recommendRecView: RecyclerView

    lateinit var productImage: ImageView
    lateinit var productName:TextView
    lateinit var productBrand:TextView
    lateinit var productPrice:TextView
    lateinit var productDes:TextView
    lateinit var product:ProductModel
    lateinit var productBackArrow: ImageView
    lateinit var productRating: RatingBar

    lateinit var addToCart: Button
    private var bl = BusinessHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        product = intent.getSerializableExtra("product") as ProductModel

        recommendProduct = arrayListOf()
        recommendRecView = findViewById(R.id.recommended_recycler_view)

        productBackArrow = findViewById(R.id.product_backarrow)
        productImage = findViewById(R.id.product_image)
        productName = findViewById(R.id.product_name)
        productBrand = findViewById(R.id.product_brand)
        productPrice = findViewById(R.id.product_price)
        productDes = findViewById(R.id.product_des)
        addToCart = findViewById(R.id.add_to_cart)
        productRating = findViewById(R.id.product_rating)

        productBackArrow.setOnClickListener(this)
        addToCart.setOnClickListener(this)
        productImage.setImageResource(product.productImage)
        productName.text = product.productName
        productBrand.text = product.productBrand
        productPrice.text = "$"+product.productPrice
        productDes.text = product.productDes
        productRating.rating = product.productRating

        recommendProduct = bl.readSuggestedProducts(product.productCategory, product.productId)

        recommendRecView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
        recommendRecView.setHasFixedSize(true)
        recommendProductAdapter = ProductAdapter(recommendProduct, this )
        recommendRecView.adapter = recommendProductAdapter

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.product_backarrow -> { super.onBackPressed(); }
            R.id.add_to_cart -> { addToCartDialog() }
            else -> return
        }
    }

    fun addToCartDialog(){

        var qua = 1
        var pPrice = productPrice.text.toString().subSequence(1, productPrice.text.toString().length).toString().toInt()

        val dialog = BottomSheetDialog(
            this, R.style.BottomSheetDialogTheme
        )

        val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
            R.layout.fragment_add_to_cart,
            findViewById<ConstraintLayout>(R.id.cartDialog)
        )

        bottomSheetView.findViewById<View>(R.id.dialoge_add_to_cart).setOnClickListener {

            pPrice *= bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                .toInt()
            val cart = Cart(product.productImage, product.productName, product.productId, product.productPrice, qua)
            bl.insertCartProduct(cart)
            dialog.dismiss()
        }

        bottomSheetView.findViewById<LinearLayout>(R.id.minusLayout).setOnClickListener {
            if(bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                    .toInt() > 1){
                qua--
                bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).setText(qua.toString())
            }
        }

        bottomSheetView.findViewById<LinearLayout>(R.id.plusLayout).setOnClickListener {
            if(bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                    .toInt() < 10){
                qua++
                bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).setText(qua.toString())
            }
        }

        dialog.setContentView(bottomSheetView)
        dialog.show()

    }

}