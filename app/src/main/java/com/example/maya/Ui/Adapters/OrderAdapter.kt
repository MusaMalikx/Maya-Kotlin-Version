package com.example.maya.Ui.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maya.R
import com.example.maya.Ui.Models.OrderModel

class OrderAdapter(private val orderList: ArrayList<OrderModel>, context: Context): RecyclerView.Adapter<OrderAdapter.ViewHolder>()  {

    val ctx: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {

        val productView = LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return ViewHolder(productView)
    }


    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {

        val order: OrderModel = orderList[position]
        holder.name.text = order.order_name
        holder.price.text = "$"+order.order_price.toString()
        holder.quantity.text = order.order_quantity.toString()

        Glide.with(ctx)
            .load(order.order_image)
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val image:ImageView = itemView.findViewById(R.id.order_image)
        val name:TextView = itemView.findViewById(R.id.order_name)
        val price:TextView = itemView.findViewById(R.id.order_price)
        val quantity:TextView = itemView.findViewById(R.id.order_quantity)
    }
}