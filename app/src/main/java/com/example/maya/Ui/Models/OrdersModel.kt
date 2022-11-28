package com.example.maya.Ui.Models

import androidx.lifecycle.ViewModel
import java.io.Serializable

data class OrdersModel(
    val productId: String = "",
    val orderNumber: Number = 0,
): ViewModel(),  Serializable {
}
