package com.example.maya.Ui.Fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.R
import com.example.maya.Ui.Adapters.CarouselAdapter
import com.example.maya.Ui.Adapters.ProductAdapter
import com.example.maya.Ui.Models.ProductModel

class HomeFragment: Fragment() {

    lateinit var newProduct:MutableList<ProductModel>
    lateinit var saleProduct:MutableList<ProductModel>
    lateinit var newProductAdapter: ProductAdapter
    lateinit var saleProductAdapter: ProductAdapter
    lateinit var newRecView:RecyclerView
    lateinit var saleRecView:RecyclerView
    lateinit var newLayout: LinearLayout
    lateinit var saleLayout:LinearLayout
    lateinit var animationView: LottieAnimationView
    lateinit var cardView: CardView
    lateinit var viewpager: ViewPager2

    private lateinit var bl:BusinessHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        newRecView = view.findViewById(R.id.newRecView)
        newLayout = view.findViewById(R.id.newLayout)
        saleRecView = view.findViewById(R.id.saleRecView)
        saleLayout = view.findViewById(R.id.saleLayout)
        cardView = view.findViewById(R.id.card_view)
        animationView = view.findViewById(R.id.animationView)

        newProduct = arrayListOf()
        saleProduct = arrayListOf()

        bl = BusinessHandler(view.context)

        initializingCarousel(view)
        initializingNewProducts()
        initializingSaleProducts()

        hideLayout()

        Handler().postDelayed({
            showLayout()
        }, 2000)

        return view

    }

    fun initializingCarousel(view: View){
        var arr : MutableList<Int> = bl.readLandscapePics()
        viewpager = view.findViewById(R.id.viewpager)
        viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewpager.adapter = CarouselAdapter(view.context, arr)
    }

    fun initializingNewProducts(){
        newProduct = bl.readNewProducts()
        newRecView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        newRecView.setHasFixedSize(true)
        newProductAdapter = ProductAdapter(newProduct, activity as Context)
        newRecView.adapter = newProductAdapter
    }

    fun initializingSaleProducts(){
        saleProduct = bl.readSaleProducts()
        saleRecView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        saleRecView.setHasFixedSize(true)
        saleProductAdapter = ProductAdapter(saleProduct, activity as Context)
        saleRecView.adapter = saleProductAdapter
    }

    private fun hideLayout(){
        animationView.playAnimation()
        animationView.loop(true)
        viewpager.visibility = View.GONE
        newLayout.visibility = View.GONE
        saleLayout.visibility = View.GONE
        cardView.visibility = View.GONE
        animationView.visibility = View.VISIBLE
    }
    private fun showLayout(){
        animationView.pauseAnimation()
        animationView.visibility = View.GONE
        viewpager.visibility = View.VISIBLE
        newLayout.visibility = View.VISIBLE
        saleLayout.visibility = View.VISIBLE
        cardView.visibility = View.VISIBLE
    }

}