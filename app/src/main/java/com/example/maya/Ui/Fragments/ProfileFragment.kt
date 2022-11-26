package com.example.maya.Ui.Fragments

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.airbnb.lottie.LottieAnimationView


import com.example.maya.R
import de.hdodenhof.circleimageview.CircleImageView



class ProfileFragment : Fragment() {

    lateinit var animationView: LottieAnimationView

    lateinit var profileImage_profileFrag: CircleImageView

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    lateinit var uploadImage_profileFrag:Button
    lateinit var profileName_profileFrag:TextView
    lateinit var profileEmail_profileFrag:TextView

    var cards: Int = 0

    lateinit var linearLayout2:LinearLayout
    lateinit var linearLayout3:LinearLayout
    lateinit var linearLayout4:LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileImage_profileFrag = view.findViewById(R.id.profileImage_profileFrag)
        val settingCd_profileFrag = view.findViewById<CardView>(R.id.settingCd_profileFrag)
        uploadImage_profileFrag = view.findViewById(R.id.uploadImage_profileFrag)
        profileName_profileFrag = view.findViewById(R.id.profileName_profileFrag)
        profileEmail_profileFrag = view.findViewById(R.id.profileEmail_profileFrag)
        animationView = view.findViewById(R.id.animationView)
        linearLayout2 = view.findViewById(R.id.linearLayout2)
        linearLayout3 = view.findViewById(R.id.linearLayout3)
        linearLayout4 = view.findViewById(R.id.linearLayout4)
        val shippingAddressCard_ProfilePage = view.findViewById<CardView>(R.id.shippingAddressCard_ProfilePage)
        val paymentMethod_ProfilePage = view.findViewById<CardView>(R.id.paymentMethod_ProfilePage)
        val cardsNumber_profileFrag:TextView = view.findViewById(R.id.cardsNumber_profileFrag)

//        cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java)
//
//        cardViewModel.allCards.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            cards = it.size
//        })

        if(cards == 0){
            cardsNumber_profileFrag.text = "You Have no Cards."
        }
        else{

        cardsNumber_profileFrag.text = "You Have "+ cards.toString() + " Cards."
        }

//        shippingAddressCard_ProfilePage.setOnClickListener {
//            startActivity(Intent(context,ShipingAddressActivity::class.java))
//        }
//
//        paymentMethod_ProfilePage.setOnClickListener {
//            startActivity(Intent(context,PaymentMethodActivity::class.java))
//        }

        hideLayout()

        Handler().postDelayed({
            showLayout()
        }, 2000)

        return view
    }

    private fun hideLayout(){
        animationView.playAnimation()
        animationView.loop(true)
        linearLayout2.visibility = View.GONE
        linearLayout3.visibility = View.GONE
        linearLayout4.visibility = View.GONE
        animationView.visibility = View.VISIBLE
    }
    private fun showLayout(){
        animationView.pauseAnimation()
        animationView.visibility = View.GONE
        linearLayout2.visibility = View.VISIBLE
        linearLayout3.visibility = View.VISIBLE
        linearLayout4.visibility = View.VISIBLE
    }

}