package com.example.maya.Ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.R
import com.example.maya.Ui.Adapters.CartAdapter
import com.example.maya.Ui.Adapters.OrderAdapter
import com.example.maya.Ui.Models.OrderModel
import com.example.maya.Ui.Models.ProductModel

class OrderActivity : AppCompatActivity() {

    lateinit var orderRecView: RecyclerView
    lateinit var orderProduct: MutableList<OrderModel>
    lateinit var orderAdapter: OrderAdapter
    lateinit var backArrow: ImageView
    lateinit var totalPrice: TextView

    lateinit var order_id: String
    lateinit var bl: BusinessHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        orderRecView = findViewById(R.id.order_recycler_view)
        backArrow = findViewById(R.id.order_backarrow)
        totalPrice = findViewById(R.id.total_order_price)

        bl = BusinessHandler(this)

        order_id = intent.getStringExtra("order_id").toString()
        Toast.makeText(this, order_id, Toast.LENGTH_SHORT).show()

        orderProduct = bl.readProductOrder(order_id)
        totalPrice.text = "$"+bl.getOrderTotal(order_id).toString()

        orderRecView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        orderRecView.setHasFixedSize(true)
        orderAdapter = OrderAdapter(orderProduct, this)
        orderRecView.adapter = orderAdapter

        backArrow.setOnClickListener {
            super.onBackPressed();
        }

    }

}