package com.example.maya.Ui.Fragments

import android.opengl.Visibility
import android.os.Build
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
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.R
import com.example.maya.Ui.Adapters.CartAdapter
import com.example.maya.Ui.Adapters.ProductAdapter
import com.example.maya.Ui.Libs.Firebase
import com.example.maya.Ui.Models.CartModel
import com.example.maya.Ui.Models.OrderModel
import com.example.maya.Ui.Models.ProductModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.abs
import kotlin.random.Random

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

    @RequiresApi(Build.VERSION_CODES.O)
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
        Handle()

        checkoutBtn.setOnClickListener {
            checkoutListner(view)
        }

        return view
    }

    fun updateTotalPrice(){
        totalPrice.text = "$" + bl.getCartTotal()
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

        val random = abs(Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)).nextLong()).toString()

        for (p in cartProduct) {
            bl.insertProductOrder(OrderModel(p.productName, random, p.productQuantity,p.productPrice.toInt(),p.productImage))
        }
        insertProducts(view)
        updateTotalPrice()
        hideLayout()
        Handle()

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

    private fun Handle(){
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
    }

}