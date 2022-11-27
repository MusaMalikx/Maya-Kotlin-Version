package com.example.maya.Ui.Fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.R


class OrdersFragment : Fragment() {

    lateinit var animationView: LottieAnimationView
    lateinit var animationViewMain: LottieAnimationView
    lateinit var emptyOrderMsgLayout : LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_orders, container, false)


        animationView = view.findViewById(R.id.animationViewLikePage)
        animationViewMain = view.findViewById(R.id.animationView)
        emptyOrderMsgLayout = view.findViewById(R.id.emptyOrderMsgLayout)

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
        }, 2000)



        return view
    }


}