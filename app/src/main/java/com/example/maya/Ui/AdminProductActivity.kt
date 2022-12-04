package com.example.maya.Ui


import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.R
import com.example.maya.Ui.Adapters.AdminProductAdapter
import com.example.maya.Ui.Models.ProductModel

class AdminProductActivity:AppCompatActivity() {
    lateinit var prodRecView: RecyclerView
    lateinit var Products: MutableList<ProductModel>
    lateinit var prodAdapter: AdminProductAdapter
    lateinit var backArrow: ImageView
    private lateinit var bl:BusinessHandler
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_products)
        getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        bl = BusinessHandler(this)
        prodRecView = findViewById(R.id.products_recycler_view)
        backArrow = findViewById(R.id.admin_product_backarrow)
        searchView = findViewById(R.id.admin_product_SearchView);

        Products = arrayListOf()
        Products = bl.readAllProducts()

        prodRecView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        prodRecView.setHasFixedSize(true)
        prodAdapter= AdminProductAdapter(Products,this, this@AdminProductActivity)
        prodRecView.adapter = prodAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Products = bl.searchProduct(query.toString())
                prodRecView.layoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
                prodRecView.setHasFixedSize(true)
                prodAdapter= AdminProductAdapter(Products,baseContext, this@AdminProductActivity)
                prodRecView.adapter = prodAdapter
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()){
                    Products = bl.readAllProducts()
                    prodRecView.layoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
                    prodRecView.setHasFixedSize(true)
                    prodAdapter= AdminProductAdapter(Products,baseContext, this@AdminProductActivity)
                    prodRecView.adapter = prodAdapter
                }
                return true
            }
        })

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