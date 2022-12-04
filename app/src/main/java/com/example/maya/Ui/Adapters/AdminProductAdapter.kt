package com.example.maya.Ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.Ui.Models.ProductModel
import com.example.maya.R
import com.bumptech.glide.Glide
import android.content.Context
import com.example.maya.Ui.ProductActivity
import java.security.ProtectionDomain
import android.content.Intent
import com.example.maya.Bl.BusinessHandler
import com.example.maya.Ui.AdminProductActivity
import com.example.maya.Ui.Libs.Firebase

class AdminProductAdapter(private val prodList:MutableList<ProductModel>,context: Context, val adminProduct: AdminProductActivity):RecyclerView.Adapter<AdminProductAdapter.ViewHolder>() {
    val ctx : Context = context
    val bl = BusinessHandler(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminProductAdapter.ViewHolder {
        val prodView = LayoutInflater.from(parent.context).inflate(R.layout.admin_product,parent,false)
        return ViewHolder(prodView)
    }
    override fun onBindViewHolder(holder: AdminProductAdapter.ViewHolder, position: Int) {
        val cur_prod : ProductModel = prodList[position]
        holder.admProdName.text = cur_prod.productName
        Glide.with(ctx).load(cur_prod.productImage).into(holder.admProdImg)
//        Firebase.firebaseAuth.currentUser?.uid
    }

    override fun getItemCount(): Int {
        return prodList.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val admProdName: TextView = itemView.findViewById(R.id.product_name)
        val admProdImg: ImageView = itemView.findViewById(R.id.product_picture)
        val delete: Button = itemView.findViewById(R.id.prod_delete_btn)
        val view : Button = itemView.findViewById(R.id.prod_view_btn)
         init {
            delete.setOnClickListener {
                bl.deleteProduct(prodList[adapterPosition].productId)
                adminProduct.updateLayout()
            }
             view.setOnClickListener {
                detailView(position )
             }
         }
    }

    private fun detailView(position: Int){
        val intent = Intent(ctx,ProductActivity::class.java)
        intent.putExtra("image",  prodList[position].productImage)
        intent.putExtra("ProductIndex", position)
        intent.putExtra("ProductFrom", "New")
        intent.putExtra("product", prodList[position])
        ctx.startActivity(intent)
    }

}