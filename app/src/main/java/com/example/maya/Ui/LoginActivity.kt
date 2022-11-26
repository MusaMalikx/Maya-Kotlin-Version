package com.example.maya.Ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.maya.R


class LoginActivity: AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login_btn = findViewById<Button>(R.id.login_btn)
        login_btn.setOnClickListener(this)
        val register_click = findViewById<TextView>(R.id.register_click)
        register_click.setOnClickListener(this)
    }

    fun loginBtnClick(){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    fun registerClick(){
        val myIntent = Intent(this, RegisterActivity::class.java)
        startActivity(myIntent)
        finish()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login_btn -> { loginBtnClick() }
            R.id.register_click -> { registerClick() }
            else -> {}
        }
    }

}