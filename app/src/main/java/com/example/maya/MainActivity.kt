package com.example.maya

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.maya.Bl.BusinessHandler
import com.example.maya.Bl.Product
import com.example.maya.Ui.LoginActivity
import com.example.maya.Ui.Models.ProductModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val bl = BusinessHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start = findViewById<Button>(R.id.btn_start)

//        addingCarouselImages()
//        addingProducts()

        btn_start.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
        }

    }

    fun addingProducts(){
        val p1 = Product(
            "Purple Suit 2-peice",
            "104",
            "1600",
            "Purple and Red chechered pattern 2 peice suit double breasted",
            5F,
            "0",
            false,
            "Armani",
            R.drawable.potrait1,
            "Suit",
            "",
        )

        val p2 = Product(
            "Grey Suit 3 peice",
            "105",
            "1900",
            "Grey suit chechered patter 3 peice",
            5F,
            "0",
            false,
            "Armani",
            R.drawable.potrait2,
            "Suit",
            "",
        )

        val p3 = Product(
            "Black Suit",
            "106",
            "2000",
            "Black suit 3 peice ",
            4.8F,
            "0",
            false,
            "Valentino",
            R.drawable.potrait3,
            "Suit",
            "",
        )

        bl.insertProduct(p1)
        bl.insertProduct(p2)
        bl.insertProduct(p3)
    }

    fun addingCarouselImages(){
        bl.insertLandscapePic(R.drawable.landscape1_1)
        bl.insertLandscapePic(R.drawable.landscape1_2)
        bl.insertLandscapePic(R.drawable.landscape1_3)
        bl.insertLandscapePic(R.drawable.landscape1_4)
        bl.insertLandscapePic(R.drawable.landscape1_5)
        bl.insertLandscapePic(R.drawable.landscape1_6)
        bl.insertLandscapePic(R.drawable.landscape1_7)
        bl.insertLandscapePic(R.drawable.landscape1_8)
        bl.insertLandscapePic(R.drawable.landscape1_9)
        bl.insertLandscapePic(R.drawable.landscape1_10)
    }

}