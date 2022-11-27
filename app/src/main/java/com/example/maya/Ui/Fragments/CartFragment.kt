package com.example.maya.Ui.Fragments

import android.os.Bundle
import android.os.Handler

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.R
import com.example.maya.Ui.Adapters.CartAdapter
import com.example.maya.Ui.Adapters.ProductAdapter
import com.example.maya.Ui.Models.CartModel
import com.example.maya.Ui.Models.ProductModel

class CartFragment : Fragment() {

    lateinit var animationView: LottieAnimationView
    lateinit var totalPriceBagFrag:TextView

    lateinit var cartProduct:ArrayList<CartModel>
    lateinit var cartAdapter: CartAdapter
    lateinit var cartRecView:RecyclerView
    lateinit var checkoutBtn:Button

    lateinit var animationViewMain: LottieAnimationView
    lateinit var bottomCartLayout:LinearLayout
    lateinit var emptyCartLayout:LinearLayout
    lateinit var cartTotalText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        cartRecView = view.findViewById(R.id.cart_recycler_view)
        animationView = view.findViewById(R.id.animationViewCartPage)
        animationViewMain = view.findViewById(R.id.animationView)
        totalPriceBagFrag = view.findViewById(R.id.total_price)
        checkoutBtn = view.findViewById(R.id.cart_checkout)

        cartProduct = arrayListOf()

        bottomCartLayout = view.findViewById(R.id.bottomCartLayout)
        emptyCartLayout = view.findViewById(R.id.emptyCartMsgLayout)
        cartTotalText = view.findViewById(R.id.cart_text)

        insertProducts()

        hideLayout()

        Handler().postDelayed({
            showLayout()
            animationView.playAnimation()
            animationView.loop(true)
            bottomCartLayout.visibility = View.GONE
            cartTotalText.visibility = View.GONE
            emptyCartLayout.visibility = View.VISIBLE

            if (cartProduct.size == 0){
                animationView.playAnimation()
                animationView.loop(true)
                bottomCartLayout.visibility = View.GONE
                cartTotalText.visibility = View.GONE
                emptyCartLayout.visibility = View.VISIBLE

            }
            else{
                emptyCartLayout.visibility = View.GONE
                bottomCartLayout.visibility = View.VISIBLE
                cartTotalText.visibility = View.VISIBLE
                animationView.pauseAnimation()
            }
        }, 2000)

        cartRecView.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.VERTICAL, false)
        cartRecView.setHasFixedSize(true)
        cartAdapter = CartAdapter(cartProduct, view.context )
        cartRecView.adapter = cartAdapter

        checkoutBtn.setOnClickListener {
            Toast.makeText(view.context, "Checkout Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun hideLayout(){
        animationViewMain.playAnimation()
        animationViewMain.loop(true)
        checkoutBtn.visibility = View.GONE
        cartRecView.visibility = View.GONE
        bottomCartLayout.visibility = View.GONE
        cartTotalText.visibility = View.GONE
        animationViewMain.visibility = View.VISIBLE
    }
    private fun showLayout(){
        animationViewMain.visibility = View.GONE
        animationViewMain.playAnimation()
        checkoutBtn.visibility = View.VISIBLE
        cartRecView.visibility = View.VISIBLE
        bottomCartLayout.visibility = View.VISIBLE
        cartTotalText.visibility = View.VISIBLE

    }

    fun insertProducts() {
        val p1 = CartModel(
            "Coat",
            "1",
            "231",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
            0.0F,
            "0",
            false,
            "levis",
            R.drawable.eight,
            "Coats",
            "Best of the best stichings"
        )
        val p2 = CartModel(
            "Coat",
            "1",
            "231",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
            0.0F,
            "0",
            false,
            "levis",
            R.drawable.seven,
            "Coats",
            "Best of the best stichings",
            12
        )
        val p3 = CartModel(
            "Coat",
            "1",
            "231",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
            0.0F,
            "0",
            false,
            "levis",
            R.drawable.six,
            "Coats",
            "Best of the best stichings",
            4
        )
        cartProduct.add(p1)
        cartProduct.add(p2)
        cartProduct.add(p3)
        cartProduct.add(p1)
    }

}