package com.example.maya.Ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.maya.R

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val login_click = findViewById<TextView>(R.id.login_click)

        login_click.setOnClickListener {
            loginClick()
        }
    }

    fun loginClick(){
        val myIntent = Intent(this, LoginActivity::class.java)
        startActivity(myIntent)
    }
}