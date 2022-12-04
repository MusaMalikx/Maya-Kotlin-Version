package com.example.maya.Ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.R
import com.example.maya.Ui.Adapters.ProductAdapter
import com.example.maya.Ui.Models.OrderModel
import com.example.maya.Ui.Models.ProductModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.Ui.Adapters.AdminProductAdapter


import com.google.android.material.bottomsheet.BottomSheetDialog

class AdminProductActivity:AppCompatActivity() {
    lateinit var prodRecView: RecyclerView
    lateinit var Products: MutableList<ProductModel>
    lateinit var prodAdapter: AdminProductAdapter
    lateinit var backArrow: ImageView
    private lateinit var bl:BusinessHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_products)
        getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        bl = BusinessHandler(this)
        prodRecView = findViewById(R.id.products_recycler_view)
        backArrow = findViewById(R.id.admin_product_backarrow)

        Products = arrayListOf()
//        Products.add(ProductModel("prod1","203","500","desc1",6.2F,"0",false,"aasda",0,"cool",""))
//        Products.add(ProductModel("prod1","203","500","desc1",6.2F,"0",false,"aasda",0,"cool",""))
//        Products.add(ProductModel("prod1","203","500","desc1",6.2F,"0",false,"aasda",0,"cool",""))
        Products = bl.readAllProducts()

        prodRecView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        prodRecView.setHasFixedSize(true)
        prodAdapter= AdminProductAdapter(Products,this, this@AdminProductActivity)
        prodRecView.adapter = prodAdapter

        backArrow.setOnClickListener{
            super.onBackPressed()
        }
    }

    fun updateLayout(){
        Products = bl.readAllProducts()
        prodRecView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        prodRecView.setHasFixedSize(true)
        prodAdapter= AdminProductAdapter(Products,this, this@AdminProductActivity)
        prodRecView.adapter = prodAdapter
    }

}