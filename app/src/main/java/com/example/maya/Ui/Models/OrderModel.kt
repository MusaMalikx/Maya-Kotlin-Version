package com.example.maya.Ui.Models

import android.widget.ImageView
import androidx.lifecycle.ViewModel

class OrderModel(
    val order_name: String = "",
    val order_id: String = "",
    val order_quantity: Int = 0,
    val order_price: Int = 0,
    val order_image: Int = 0
): ViewModel() {
}