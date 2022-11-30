package com.example.maya.Ui.Libs

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object Firebase {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
}