package com.example.maya.Ui.Fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.R

class CartFragment : Fragment() {

    lateinit var cartRecView:RecyclerView
    lateinit var animationView: LottieAnimationView
    lateinit var totalPriceBagFrag:TextView
    lateinit var Item:ArrayList<String>
     var sum:Int = 0

//    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        cartRecView = view.findViewById(R.id.cartRecView)
        animationView = view.findViewById(R.id.animationViewCartPage)
        totalPriceBagFrag = view.findViewById(R.id.totalPriceCartFrag)
        val bottomCartLayout:LinearLayout = view.findViewById(R.id.bottomCartLayout)
        val emptyBagMsgLayout:LinearLayout = view.findViewById(R.id.emptyCartMsgLayout)
        val MybagText:TextView = view.findViewById(R.id.MyCartText)
        Item = arrayListOf()


        animationView.playAnimation()
        animationView.loop(true)
        bottomCartLayout.visibility = View.GONE
        MybagText.visibility = View.GONE
        emptyBagMsgLayout.visibility = View.VISIBLE

        if (Item.size == 0){
            animationView.playAnimation()
            animationView.loop(true)
            bottomCartLayout.visibility = View.GONE
            MybagText.visibility = View.GONE
            emptyBagMsgLayout.visibility = View.VISIBLE

        }
        else{
            emptyBagMsgLayout.visibility = View.GONE
            bottomCartLayout.visibility = View.VISIBLE
            MybagText.visibility = View.VISIBLE
            animationView.pauseAnimation()
        }




        return view
    }

}