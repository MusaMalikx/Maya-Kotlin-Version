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

//        this.deleteDatabase("MAYA_DB")
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
            "Grey Suit 3 piece",
            "105",
            "1900",
            "Grey suit checkered pattern 3 piece",
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
            "Black suit 3 piece ",
            4.8F,
            "0",
            false,
            "Valentino",
            R.drawable.potrait3,
            "Suit",
            "",
        )
        val p4 = Product(
            "Navy Blue Suit 2 piece",
            "107",
            "2000",
            "Navy Blue suit 2 piece semi formal",
            3.9F,
            "0",
            false,
            "Armani",
            R.drawable.potrait4,
            "Suit",
            "",
        )
        val p5 = Product(
            "Dark Blue Tattered Jeans",
            "309",
            "200",
            "Dark Blue Tattered Jeans Female ",
            4.8F,
            "0",
            false,
            "Mango",
            R.drawable.potrait5,
            "Trousers",
            "",
        )
        val p6 = Product(
            "Blue Jeans Femalet",
            "301",
            "150",
            "Washed Blue Jeans Female ",
            4.0F,
            "10",
            true,
            "Outfitters",
            R.drawable.potrait6,
            "Trousers",
            "",
        )
        val p7 = Product(
            "Blue Jeans Male",
            "302",
            "190",
            "Dark Blue Jeans Male ",
            4.0F,
            "25",
            true,
            "Outfitters",
            R.drawable.potrait7,
            "Trousers",
            "",
        )
        val p8 = Product(
            "Casual ButtonUP",
            "203",
            "190",
            "Casual Buttonup with red white and black stripes ",
            3.8F,
            "15",
            true,
            "Outfitters",
            R.drawable.potrait8,
            "ButtonUp",
            "",
        )
        val p9 = Product(
            "Blue Chino",
            "303",
            "120",
            "Blue cotton chino pants ",
            3.6F,
            "20",
            true,
            "Outfitters",
            R.drawable.potrait9,
            "Trousers",
            "",
        )
        val p10 = Product(
            "Blue Bell Pants",
            "304",
            "230",
            "Dark Blue pants with bell bottoms female",
            4.8F,
            "10",
            true,
            "Mango",
            R.drawable.potrait10,
            "Trousers1",
            "",
        )
        val p11 = Product(
            "Grey Blue tattered Jeans",
            "305",
            "200",
            "Grey blue tattered jeans unisex",
            4.8F,
            "0",
            false,
            "Mango",
            R.drawable.potrait11,
            "Trousers",
            "",
        )
        val p12 = Product(
            "Checkered Button up",
            "204",
            "310",
            "Checkered Button up red grey and white",
            4.2F,
            "0",
            false,
            "Mango",
            R.drawable.potrait12,
            "ButtonUp",
            "",
        )
        val p13 = Product(
            "Ivory over coat",
            "108",
            "700",
            "Ivory over coat female",
            4.1F,
            "0",
            false,
            "Armani",
            R.drawable.potrait13,
            "Coat",
            "",
        )
        val p14 = Product(
            "Grey casual coat",
            "109",
            "600",
            "casual grey coat 4 pockets",
            4.2F,
            "0",
            false,
            "Mango",
            R.drawable.potrait14,
            "Coat",
            "",
        )
        val p15 = Product(
            "Regrets Tee",
            "205",
            "190",
            "White T-Shirt with red accents",
            3.2F,
            "10",
            true,
            "Mango",
            R.drawable.potrait15,
            "T Shirt",
            "",
        )
        val p16 = Product(
            "Blue Washed Jeans",
            "306",
            "200",
            "Washed Blue Jeans male ",
            3.8F,
            "0",
            false,
            "Outfitters",
            R.drawable.potrait16,
            "Trousers",
            "",
        )
        val p17 = Product(
            "Blue Jeans",
            "310",
            "210",
            "Blue Jeans Female",
            2.7F,
            "15",
            true,
            "Dr Martens",
            R.drawable.potrait17,
            "Trousers",
            "",
        )
        val p18 = Product(
            "Dark Blue Jeans",
            "307",
            "250",
            "Dark Blue Jeans",
            4.5F,
            "0",
            false,
            "Outfitters",
            R.drawable.potrait18,
            "Trousers",
            "",
        )
        val p19 = Product(
            "Grey checkered trouser",
            "308",
            "350",
            "Grey checkered trouser formal",
            4.9F,
            "10",
            true,
            "Mango",
            R.drawable.potrait19,
            "Trousers",
            "",
        )
        val p20 = Product(
            "Blue buttonupt",
            "206",
            "170",
            "Plain Sky blue Buttonup",
            4.2F,
            "10",
            true,
            "Mango",
            R.drawable.potrait20,
            "ButtonUp",
            "",
        )
        val p21 = Product(
            "Yellow t shirt",
            "207",
            "160",
            "Yellow Tshirt plain\"",
            4.0F,
            "0",
            false,
            "Mango",
            R.drawable.potrait21,
            "T Shirt",
            "",
        )
        val p22 = Product(
            "Blue Polo Shirt",
            "208",
            "300",
            "Blue Lacoste Polo Shirt",
            5.0F,
            "0",
            false,
            "Lacoste",
            R.drawable.potrait22,
            "T Shirt",
            "",
        )


        bl.insertProduct(p1)
        bl.insertProduct(p2)
        bl.insertProduct(p3)
        bl.insertProduct(p4)
        bl.insertProduct(p5)
        bl.insertProduct(p6)
        bl.insertProduct(p7)
        bl.insertProduct(p8)
        bl.insertProduct(p9)
        bl.insertProduct(p10)
        bl.insertProduct(p11)
        bl.insertProduct(p12)
        bl.insertProduct(p13)
        bl.insertProduct(p14)
        bl.insertProduct(p15)
        bl.insertProduct(p16)
        bl.insertProduct(p17)
        bl.insertProduct(p18)
        bl.insertProduct(p19)
        bl.insertProduct(p20)
        bl.insertProduct(p21)
        bl.insertProduct(p22)

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