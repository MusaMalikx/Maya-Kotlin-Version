package com.example.maya.Ui.Models

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import java.io.Serializable

data class UserModel(
    var userName:String = "",
    var userID:String = "",
    var userEmail:String= "",
    var admin:Boolean = false
):ViewModel(), Serializable{}
