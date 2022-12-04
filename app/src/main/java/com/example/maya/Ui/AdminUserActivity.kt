package com.example.maya.Ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maya.R
import com.example.maya.Ui.Models.UserModel
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.Bl.BusinessHandler
import com.example.maya.Ui.Adapters.AdminUserAdapter
import com.example.maya.Ui.Libs.Firebase


import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AdminUserActivity:AppCompatActivity() {
    lateinit var userRecView: RecyclerView
    lateinit var userList: MutableList<UserModel>
    lateinit var userAdapter: AdminUserAdapter
    lateinit var backArrow: ImageView
    private lateinit var bl:BusinessHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_users)
        getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        bl = BusinessHandler(this)
        userRecView = findViewById(R.id.user_recycler_view)
        backArrow = findViewById(R.id.user_backarrow)

        readUsers()
//        userList = bl.readUser()
//        userList.add(UserModel("prod1","203",500, "fads"))
//        userList.add(UserModel("prod1","203",500, "fads"))
//        userList.add(UserModel("prod1","203",500, "fads"))
//
//        userList = bl.readNewProducts()

//        userRecView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        userRecView.setHasFixedSize(true)
//        userAdapter = AdminUserAdapter(userList,this)
//        userRecView.adapter = userAdapter

        backArrow.setOnClickListener{
            super.onBackPressed()
        }
    }

    private fun readUsers(){
        val list: MutableList<UserModel> = arrayListOf()
        Firebase.firebaseDatabaseUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val value: UserModel? = postSnapshot.getValue(UserModel::class.java)
                    val i = UserModel(value?.userName.toString(),value?.userID.toString(), value?.userEmail.toString())

                    if (i.userID != Firebase.firebaseAuth.currentUser?.uid){
                        list.add(i)
                    }
                }

                userList = list
                userRecView.layoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
                userRecView.setHasFixedSize(true)
                userAdapter = AdminUserAdapter(userList,baseContext)
                userRecView.adapter = userAdapter

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}