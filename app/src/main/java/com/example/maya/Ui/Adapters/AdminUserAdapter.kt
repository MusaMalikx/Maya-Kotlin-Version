package com.example.maya.Ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.Ui.Models.UserModel
import com.example.maya.R
import com.bumptech.glide.Glide
import android.content.Context
import com.example.maya.Ui.ProductActivity
import java.security.ProtectionDomain
import android.content.Intent
import android.graphics.BitmapFactory
import com.example.maya.Ui.Libs.Firebase
import java.io.File

class AdminUserAdapter(private val userList:MutableList<UserModel>,context: Context):RecyclerView.Adapter<AdminUserAdapter.ViewHolder>() {
    val ctx : Context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminUserAdapter.ViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.admin_user,parent,false)
        return ViewHolder(userView)
    }
    override fun onBindViewHolder(holder: AdminUserAdapter.ViewHolder, position: Int) {
        val cur_user : UserModel = userList[position]
        holder.admUserName.text = cur_user.userName
        holder.admEmailName.text = cur_user.userEmail

        val usersImageRef = Firebase.firebaseStorage.getReference().child("${cur_user.userID}.jpg")

        val localFile = File.createTempFile("images", "bmp")

        usersImageRef.getFile(localFile).addOnSuccessListener {
            holder.admUserImg.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()))
        }.addOnFailureListener {
//            Toast.makeText(view?.context, "Not Found or Image Read from firebase error!", Toast.LENGTH_SHORT).show()
        }
//        Glide.with(ctx).load(cur_user.userPicture).into(holder.admUserImg)
//        Firebase.firebaseAuth.currentUser?.uid
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val admUserName: TextView = itemView.findViewById(R.id.admin_user_name)
        val admUserImg: ImageView = itemView.findViewById(R.id.user_picture)
        val admEmailName: TextView = itemView.findViewById(R.id.admin_user_email)

    }

}