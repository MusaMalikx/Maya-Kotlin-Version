package com.example.maya.Ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.R
import com.example.maya.Ui.Adapters.ProductAdapter
import com.example.maya.Ui.Models.ProductModel

class ProductActivity: AppCompatActivity(), View.OnClickListener {

    lateinit var recommendProduct:ArrayList<ProductModel>
    lateinit var recommendProductAdapter: ProductAdapter
    lateinit var recommendRecView: RecyclerView

    lateinit var productImage: ImageView
    lateinit var productName:TextView
    lateinit var productBrand:TextView
    lateinit var productPrice:TextView
    lateinit var productDes:TextView
    lateinit var product:ProductModel
    lateinit var productBackArrow: ImageView

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

        productBackArrow.setOnClickListener(this)
        productImage.setImageResource(product.productImage)
        productName.text = product.productName
        productBrand.text = product.productBrand
        productPrice.text = "$"+product.productPrice
        productDes.text = product.productDes

        insertProducts()

        recommendRecView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
        recommendRecView.setHasFixedSize(true)
        recommendProductAdapter = ProductAdapter(recommendProduct, this )
        recommendRecView.adapter = recommendProductAdapter

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.product_backarrow -> { super.onBackPressed(); }
//            R.id.register_click -> { registerClick() }
            else -> return
        }
    }

    fun insertProducts() {
        val p1 = ProductModel(
            "Coat",
            "1",
            "231",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
            0.0F,
            "0",
            false,
            "levis",
            R.drawable.eight,
            "Coats",
            "Best of the best stichings"
        )
        recommendProduct.add(p1)
        recommendProduct.add(p1)
        recommendProduct.add(p1)
        recommendProduct.add(p1)
    }
}