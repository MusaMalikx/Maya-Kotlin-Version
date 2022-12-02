package com.example.maya.Ui.Libs

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


object Firebase {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var firebaseStorage = FirebaseStorage.getInstance()
}