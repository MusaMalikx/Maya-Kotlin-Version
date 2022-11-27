package com.example.maya.Ui.Adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.Ui.Models.ProductModel
import com.bumptech.glide.Glide
import com.example.maya.R
import com.example.maya.Ui.ProductActivity

class ProductAdapter(private val productList: ArrayList<ProductModel>, context: Context): RecyclerView.Adapter<ProductAdapter.ViewHolder>()  {

    val ctx: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {

        val productView = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ViewHolder(productView)
    }


    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {

        val product: ProductModel = productList[position]
        holder.productBrandName_singleProduct.text = product.productBrand
        holder.productName_singleProduct.text = product.productName
        holder.productPrice_singleProduct.text = "$"+product.productPrice
        holder.productRating_singleProduct.rating = product.productRating

        Glide.with(ctx)
            .load(product.productImage)
//            .placeholder(bn)
            .into(holder.productImage_singleProduct)


        if(product.productHave == true){
            holder.discountTv_singleProduct.text = product.productDisCount
            holder.discount_singleProduct.visibility = View.VISIBLE
        }

        if(product.productHave == false){

            holder.discount_singleProduct.visibility = View.VISIBLE
            holder.discountTv_singleProduct.text = "New"

        }

        holder.itemView.setOnClickListener {
            goDetailsPage(position)
        }

    }

    override fun getItemCount(): Int {
         return productList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val productImage_singleProduct:ImageView = itemView.findViewById(R.id.productImage_singleProduct)
//        val productAddToFav_singleProduct:ImageView = itemView.findViewById(R.id.productAddToFav_singleProduct)
        val productRating_singleProduct:RatingBar = itemView.findViewById(R.id.productRating_singleProduct)
        val productBrandName_singleProduct:TextView = itemView.findViewById(R.id.productBrandName_singleProduct)
        val discountTv_singleProduct:TextView = itemView.findViewById(R.id.discountTv_singleProduct)
        val productName_singleProduct:TextView = itemView.findViewById(R.id.productName_singleProduct)
        val productPrice_singleProduct:TextView = itemView.findViewById(R.id.productPrice_singleProduct)
        val discount_singleProduct = itemView.findViewById<LinearLayout>(R.id.discount_singleProduct)


    }

    private fun goDetailsPage(position: Int) {
        val intent = Intent(ctx , ProductActivity::class.java)
        intent.putExtra("image",  productList[position].productImage)
        intent.putExtra("ProductIndex", position)
        intent.putExtra("ProductFrom", "New")
        intent.putExtra("product", productList[position])
        ctx.startActivity(intent)
    }
}