package com.example.maya.Ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.R
import com.example.maya.Ui.Adapters.CartAdapter
import com.example.maya.Ui.Adapters.OrderAdapter
import com.example.maya.Ui.Models.OrderModel
import com.example.maya.Ui.Models.ProductModel

class OrderActivity : AppCompatActivity() {

    lateinit var orderRecView: RecyclerView
    lateinit var orderProduct: ArrayList<OrderModel>
    lateinit var orderAdapter: OrderAdapter
    lateinit var backArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        orderRecView = findViewById(R.id.order_recycler_view)
        backArrow = findViewById(R.id.order_backarrow)

        orderProduct = arrayListOf()
        orderProduct.add(OrderModel("Coat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co", 12, 123, R.drawable.twelve))
        orderProduct.add(OrderModel("Coat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co", 12, 123, R.drawable.twelve))
        orderProduct.add(OrderModel("Coat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co", 12, 123, R.drawable.twelve))
        orderProduct.add(OrderModel("Coat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co", 12, 123, R.drawable.twelve))

        orderRecView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        orderRecView.setHasFixedSize(true)
        orderAdapter = OrderAdapter(orderProduct, this )
        orderRecView.adapter = orderAdapter

        backArrow.setOnClickListener {
            super.onBackPressed();
        }

    }

}