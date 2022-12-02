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
        // Inflate the layout for this fragment
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
//        insertProducts()
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
//        var arr: ArrayList<Int> = arrayListOf(R.drawable.landscape1_1,R.drawable.landscape1_2,R.drawable.landscape1_3,R.drawable.landscape1_4,R.drawable.landscape1_5,R.drawable.landscape1_6,R.drawable.landscape1_7,R.drawable.landscape1_8,R.drawable.landscape1_9,R.drawable.landscape1_10)
//        println(arr)
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

    fun insertProducts(){
//        val p1 = ProductModel("Coat", "1", "231", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
//        0.0F, "0", false, "levis", R.drawable.eight,"Coats", "Best of the best stichings")
//        newProduct.add(p1)
//        newProduct.add(p1)
//        newProduct.add(p1)
//        newProduct.add(p1)

        val p2 = ProductModel("Sweater", "1", "231", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
            0.0F, "15%", true, "levis", R.drawable.seven,"Coats", "Best of the best stichings")
        saleProduct.add(p2)
        saleProduct.add(p2)
        saleProduct.add(p2)
        saleProduct.add(p2)
    }

    private fun hideLayout(){
        animationView.playAnimation()
        animationView.loop(true)
        viewpager.visibility = View.GONE
//        coverRecView.visibility = View.GONE
        newLayout.visibility = View.GONE
        saleLayout.visibility = View.GONE
        cardView.visibility = View.GONE
        animationView.visibility = View.VISIBLE
    }
    private fun showLayout(){
        animationView.pauseAnimation()
        animationView.visibility = View.GONE
        viewpager.visibility = View.VISIBLE
//        coverRecView.visibility = View.VISIBLE
        newLayout.visibility = View.VISIBLE
        saleLayout.visibility = View.VISIBLE
        cardView.visibility = View.VISIBLE
    }

}