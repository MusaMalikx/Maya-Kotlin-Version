package com.example.maya.Ui.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maya.R
import com.example.maya.Ui.Models.CartModel

class CartAdapter(private val cartList: ArrayList<CartModel>, context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>()  {

    val ctx: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {

        val cartView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        return ViewHolder(cartView)
    }


    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {

        val cart: CartModel = cartList[position]
        holder.cartProductName.text = cart.productName
        holder.cartProductPrice.text = "$"+ cart.productPrice
        holder.cartProductquantity.text = cart.productQuantity.toString()
        Glide.with(ctx)
            .load(cart.productImage)
            .into(holder.cartProductImage)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val cartProductName: TextView = itemView.findViewById(R.id.cart_name)
        val cartProductPrice: TextView = itemView.findViewById(R.id.cart_price)
        val cartProductImage: ImageView = itemView.findViewById(R.id.cart_image)
        val cartProductquantity: TextView = itemView.findViewById(R.id.cart_quantity)
        val cartDec: ImageView = itemView.findViewById(R.id.cart_dec)
        val cartInc: ImageView = itemView.findViewById(R.id.cart_inc)

        init {
            cartDec.setOnClickListener{
                var quant = cartProductquantity.text.toString().toInt()
                if(quant > 1){
                    quant--
                }
                cartProductquantity.text = quant.toString()
            }

            cartInc.setOnClickListener{
                var quant = cartProductquantity.text.toString().toInt()
                quant++
                cartProductquantity.text = quant.toString()
            }

        }

    }

}