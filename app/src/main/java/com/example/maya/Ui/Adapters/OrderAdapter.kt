package com.example.maya.Ui.Adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.R
import com.example.maya.Ui.Models.OrderModel
import com.example.maya.Ui.ProductActivity

class OrderAdapter(private val orderList: ArrayList<OrderModel>, context: Context): RecyclerView.Adapter<OrderAdapter.ViewHolder>()  {

    val ctx: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {

        val productView = LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return ViewHolder(productView)
    }


    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {

        val order: OrderModel = orderList[position]
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
        val intent = Intent(ctx , ProductActivity::class.java)
        intent.putExtra("order", orderList[position])
        ctx.startActivity(intent)
    }
}