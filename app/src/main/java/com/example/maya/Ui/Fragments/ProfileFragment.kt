package com.example.maya.Ui.Fragments


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.R
import com.example.maya.Ui.Libs.Firebase
import com.example.maya.Ui.LoginActivity
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
    lateinit var linearLayout4:LinearLayout


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
        linearLayout4 = view.findViewById(R.id.linearLayout4)
        logout_btn = view.findViewById(R.id.logout_btn)

        profileName.text = Firebase.firebaseAuth.currentUser!!.displayName
        profileEmail.text = Firebase.firebaseAuth.currentUser!!.email

        downloadProfileImage()

        hideLayout()

        Handler().postDelayed({
            showLayout()
        }, 2000)

        logout_btn.setOnClickListener {
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

    private fun uploadProfileImage(data: Intent){
        val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, data.data)
        profileImage.setImageBitmap(bitmap)

        val usersRef: StorageReference = Firebase.firebaseStorage.getReference().child("${Firebase.firebaseAuth.currentUser!!.uid}.jpg")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = usersRef.putBytes(data)
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
            Toast.makeText(view?.context, "Not Found or Image Read from firebase error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideLayout(){
        animationView.playAnimation()
        animationView.loop(true)
        linearLayout2.visibility = View.GONE
        linearLayout3.visibility = View.GONE
        linearLayout4.visibility = View.GONE
        logout_btn.visibility = View.GONE
        animationView.visibility = View.VISIBLE
    }
    private fun showLayout(){
        animationView.pauseAnimation()
        animationView.visibility = View.GONE
        logout_btn.visibility = View.VISIBLE
        linearLayout2.visibility = View.VISIBLE
        linearLayout3.visibility = View.VISIBLE
        linearLayout4.visibility = View.VISIBLE
    }

}