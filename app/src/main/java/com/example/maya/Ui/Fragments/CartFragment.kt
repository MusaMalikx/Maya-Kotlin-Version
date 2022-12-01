package com.example.maya.Ui.Fragments

import android.opengl.Visibility
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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.R
import com.example.maya.Ui.Adapters.CartAdapter
import com.example.maya.Ui.Adapters.ProductAdapter
import com.example.maya.Ui.Models.CartModel
import com.example.maya.Ui.Models.ProductModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class CartFragment : Fragment() {

    lateinit var animationView: LottieAnimationView
    lateinit var totalPrice:TextView

    lateinit var cartProduct:MutableList<CartModel>
    lateinit var cartAdapter: CartAdapter
    lateinit var cartRecView:RecyclerView
    lateinit var checkoutBtn:Button

    lateinit var animationViewMain: LottieAnimationView
    lateinit var bottomCartLayout:LinearLayout
    lateinit var emptyCartLayout:LinearLayout
    lateinit var cartTotalText:TextView

    lateinit var animationViewDialog: LottieAnimationView
    lateinit var successLayout: LinearLayout

    lateinit var bl:BusinessHandler

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
        totalPrice = view.findViewById(R.id.total_price)
        checkoutBtn = view.findViewById(R.id.cart_checkout)

        cartProduct = arrayListOf()

        bottomCartLayout = view.findViewById(R.id.bottomCartLayout)
        emptyCartLayout = view.findViewById(R.id.emptyCartMsgLayout)
        cartTotalText = view.findViewById(R.id.cart_text)
        bl = BusinessHandler(view.context)

        insertProducts(view)

        totalPrice.text = "$" + bl.getCartTotal()

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

        checkoutBtn.setOnClickListener {
            checkoutListner(view)
        }

        return view
    }

    fun updateTotalPrice(){
        totalPrice.text = "$" + bl.getCartTotal()
    }

    private fun checkoutListner(view: View){

        val dialog = BottomSheetDialog(
            view.context, R.style.BottomSheetDialogTheme
        )

        val bottomSheetView = LayoutInflater.from(view.context.applicationContext).inflate(
            R.layout.fragment_order_confirmed,
            view.findViewById<ConstraintLayout>(R.id.order_dialogue)
        )

        animationViewDialog = bottomSheetView.findViewById(R.id.animationViewDia)
        successLayout = bottomSheetView.findViewById(R.id.success_layout)

        animationViewDialog.playAnimation()
        animationViewDialog.loop(true)
        successLayout.visibility = View.GONE

        Handler().postDelayed({
            animationViewDialog.pauseAnimation()
            animationViewDialog.visibility = View.GONE
            successLayout.visibility = View.VISIBLE
        }, 1500)

        dialog.setContentView(bottomSheetView)
        dialog.show()

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

    fun insertProducts(view: View) {
        cartProduct = bl.readCartProducts()
        println(cartProduct)
        cartRecView.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.VERTICAL, false)
        cartRecView.setHasFixedSize(true)
        cartAdapter = CartAdapter(cartProduct, view.context, this@CartFragment )
        cartRecView.adapter = cartAdapter
//        val p1 = CartModel(
//            "Coat",
//            "1",
//            "231",
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
//            0.0F,
//            "0",
//            false,
//            "levis",
//            R.drawable.eight,
//            "Coats",
//            "Best of the best stichings"
//        )
//        val p2 = CartModel(
//            "Coat",
//            "1",
//            "231",
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
//            0.0F,
//            "0",
//            false,
//            "levis",
//            R.drawable.seven,
//            "Coats",
//            "Best of the best stichings",
//            12
//        )
//        val p3 = CartModel(
//            "Coat",
//            "1",
//            "231",
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea co",
//            0.0F,
//            "0",
//            false,
//            "levis",
//            R.drawable.six,
//            "Coats",
//            "Best of the best stichings",
//            4
//        )
//        cartProduct.add(p1)
//        cartProduct.add(p2)
//        cartProduct.add(p3)
//        cartProduct.add(p1)
    }

}