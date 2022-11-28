package com.example.maya.Ui.Fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.R
import com.example.maya.Ui.Adapters.OrdersAdapter
import com.example.maya.Ui.Models.OrdersModel


class OrdersFragment : Fragment() {

    lateinit var animationView: LottieAnimationView
    lateinit var animationViewMain: LottieAnimationView
    lateinit var emptyOrderMsgLayout : LinearLayout

    lateinit var orderProduct: ArrayList<OrdersModel>
    lateinit var orderAdapter: OrdersAdapter
    lateinit var orderRecView: RecyclerView

    lateinit var orderText: TextView
    lateinit var orderCard:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_orders, container, false)


        animationView = view.findViewById(R.id.animationViewLikePage)
        animationViewMain = view.findViewById(R.id.animationView)
        emptyOrderMsgLayout = view.findViewById(R.id.emptyOrderMsgLayout)
        orderText = view.findViewById(R.id.order_text)
        orderCard = view.findViewById(R.id.orders_card)

//        orderProduct = ViewModelProvider(this)[OrderModel::class.java]
        orderProduct = arrayListOf()
        orderProduct.add(OrdersModel(orderNumber = 12))
        orderProduct.add(OrdersModel(orderNumber = 13))
        orderProduct.add(OrdersModel(orderNumber = 14))
        orderProduct.add(OrdersModel(orderNumber = 15))

        orderRecView = view.findViewById(R.id.orders_recycler_view)
        orderRecView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
        orderRecView.setHasFixedSize(true)
        orderAdapter = OrdersAdapter(orderProduct, activity as Context)
        orderRecView.adapter = orderAdapter

        orderRecView.visibility = View.GONE
        orderText.visibility = View.GONE
        orderCard.visibility = View.GONE
        emptyOrderMsgLayout.visibility = View.GONE
        animationViewMain.visibility = View.VISIBLE
        animationViewMain.playAnimation()
        animationViewMain.loop(true)

        Handler().postDelayed({
            animationViewMain.pauseAnimation()
            animationViewMain.visibility = View.GONE
            emptyOrderMsgLayout.visibility = View.VISIBLE
            animationView.playAnimation()
            animationView.loop(true)

            if (orderProduct.size == 0){
                animationView.playAnimation()
                animationView.loop(true)
                orderCard.visibility = View.GONE
                orderRecView.visibility = View.GONE
                orderText.visibility = View.GONE

            }
            else{
                animationView.pauseAnimation()
                animationView.playAnimation()
                animationView.loop(true)
                emptyOrderMsgLayout.visibility = View.GONE
                orderCard.visibility = View.VISIBLE
                orderRecView.visibility = View.VISIBLE
                orderText.visibility = View.VISIBLE
            }

        }, 2000)



        return view
    }


}