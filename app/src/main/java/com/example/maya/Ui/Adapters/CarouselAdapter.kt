package com.example.maya.Ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.R


class CarouselAdapter(private val context: Context, val arrayList: MutableList<Int>) :
    RecyclerView.Adapter<CarouselAdapter.MyViewHolder>() {
//    private var arrayList = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_carousel, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView

        init {
            image = itemView.findViewById(R.id.carousel_image)
        }
    }

//    init {
//        this.arrayList = arrayList
//    }
}