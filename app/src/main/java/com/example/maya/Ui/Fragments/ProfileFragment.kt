package com.example.maya.Ui.Fragments


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.Bl.Product
import com.example.maya.R
import com.example.maya.Ui.AdminProductActivity
import com.example.maya.Ui.AdminUserActivity
import com.example.maya.Ui.Libs.Firebase
import com.example.maya.Ui.LoginActivity
import com.example.maya.Ui.MediaService
import com.example.maya.Ui.Models.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


class ProfileFragment : Fragment() {

    lateinit var animationView: LottieAnimationView

    lateinit var profileImage: CircleImageView

    lateinit var logout_btn: Button

    lateinit var uploadImage:Button
    lateinit var profileName:TextView
    lateinit var profileEmail:TextView

    lateinit var linearLayout2:LinearLayout
    lateinit var linearLayout3:LinearLayout
    lateinit var adminLinearLayout4:LinearLayout

    lateinit var adminProducts: CardView
    lateinit var adminUsers: CardView
    lateinit var adminCardView: CardView

    lateinit var adminAddProducts: Button
    lateinit var adminAddCarousel: Button
    lateinit var adminDelDb: Button

    lateinit var bl: BusinessHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileImage= view.findViewById(R.id.profile_image)
        uploadImage = view.findViewById(R.id.upload_image)
        profileName = view.findViewById(R.id.profile_name)
        profileEmail = view.findViewById(R.id.profile_email)
        animationView = view.findViewById(R.id.animationView)
        linearLayout2 = view.findViewById(R.id.linearLayout2)
        linearLayout3 = view.findViewById(R.id.linearLayout3)
        adminLinearLayout4 = view.findViewById(R.id.admin_linearLayout4)
        logout_btn = view.findViewById(R.id.logout_btn)
        adminCardView = view.findViewById(R.id.admin_card_btn)

        profileName.text = Firebase.firebaseAuth.currentUser!!.displayName
        profileEmail.text = Firebase.firebaseAuth.currentUser!!.email

        adminProducts = view.findViewById(R.id.admin_product_card)
        adminUsers = view.findViewById(R.id.admin_user_card)

        adminAddProducts = view.findViewById(R.id.profile_btn_products)
        adminAddCarousel = view.findViewById(R.id.profile_btn_carousel)
        adminDelDb = view.findViewById(R.id.profile_btn_del_db)

        bl = BusinessHandler(view.context)
        downloadProfileImage()

        hideLayout()

        Handler().postDelayed({
            showLayout()
        }, 2000)

        logout_btn.setOnClickListener {
            activity?.stopService(Intent(view?.context, MediaService::class.java))
            Firebase.firebaseAuth.signOut()
            Toast.makeText(view.context, "Signed out successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(view.context, LoginActivity::class.java))
        }

        uploadImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
        }

        adminProducts.setOnClickListener {
            startActivity(Intent(view.context, AdminProductActivity::class.java))
        }

        adminUsers.setOnClickListener {
            startActivity(Intent(view.context, AdminUserActivity::class.java))
        }

//        this.deleteDatabase("MAYA_DB")
////        addingCarouselImages()
////        addingProducts()

        adminAddProducts.setOnClickListener { addingProducts() }
        adminAddCarousel.setOnClickListener { addingCarouselImages() }
        adminDelDb.setOnClickListener{ activity?.deleteDatabase("MAYA_DB") }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(view?.context,"Picture Selected!", Toast.LENGTH_SHORT).show()
            if(data == null || data.data == null){
                return
            }

            try {
                uploadProfileImage(data)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun adminCheck(){
        Firebase.firebaseDatabaseUsers.ref.child(Firebase.firebaseAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value: UserModel? = dataSnapshot.getValue(UserModel::class.java)
//                    Toast.makeText(view?.context, ""+value?.admin, Toast.LENGTH_SHORT).show()
                    if (value?.admin == true){
                        adminLinearLayout4.visibility = View.VISIBLE
                        adminCardView.visibility = View.VISIBLE
                    }
                    else {
                        adminLinearLayout4.visibility = View.GONE
                        adminCardView.visibility = View.GONE
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }


    private fun uploadProfileImage(data: Intent){
        val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, data.data)
        profileImage.setImageBitmap(bitmap)

        val usersRef: StorageReference = Firebase.firebaseStorage.getReference().child("${Firebase.firebaseAuth.currentUser!!.uid}.jpg")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = usersRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Toast.makeText(view?.context, "Image Insertion to firebase Failed!", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(view?.context, "Image Added to firebase Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun downloadProfileImage(){
        val usersImageRef = Firebase.firebaseStorage.getReference().child("${Firebase.firebaseAuth.currentUser?.uid}.jpg")

        val localFile = File.createTempFile("images", "bmp")

        usersImageRef.getFile(localFile).addOnSuccessListener {
            profileImage.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()))
        }.addOnFailureListener {
//            Toast.makeText(view?.context, "Not Found or Image Read from firebase error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideLayout(){
        animationView.playAnimation()
        animationView.loop(true)
        linearLayout2.visibility = View.GONE
        linearLayout3.visibility = View.GONE
        adminLinearLayout4.visibility = View.GONE
        adminCardView.visibility = View.GONE
        logout_btn.visibility = View.GONE
        animationView.visibility = View.VISIBLE
    }
    private fun showLayout(){
        animationView.pauseAnimation()
        animationView.visibility = View.GONE
        logout_btn.visibility = View.VISIBLE
        linearLayout2.visibility = View.VISIBLE
        linearLayout3.visibility = View.VISIBLE
//        adminLinearLayout4.visibility = View.VISIBLE
        adminCheck()
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