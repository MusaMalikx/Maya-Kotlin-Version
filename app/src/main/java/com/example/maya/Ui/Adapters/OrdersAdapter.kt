package com.example.maya.Ui.Adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.R
import com.example.maya.Ui.Models.OrdersModel
import com.example.maya.Ui.OrderActivity
import com.example.maya.Ui.ProductActivity

class OrdersAdapter(private val orderList: ArrayList<OrdersModel>, context: Context): RecyclerView.Adapter<OrdersAdapter.ViewHolder>()  {

    val ctx: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.ViewHolder {

        val productView = LayoutInflater.from(parent.context).inflate(R.layout.item_orders,parent,false)
        return ViewHolder(productView)
    }


    override fun onBindViewHolder(holder: OrdersAdapter.ViewHolder, position: Int) {

        val order: OrdersModel = orderList[position]
        holder.orderNumber.text = "# "+order.orderNumber

        holder.itemView.setOnClickListener {
            goOrdersPage(position)
        }

    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

       val orderNumber:TextView = itemView.findViewById(R.id.order_number)


    }

    private fun goOrdersPage(position: Int) {
        val intent = Intent(ctx , OrderActivity::class.java)
        intent.putExtra("order", orderList[position])
        ctx.startActivity(intent)
    }
}