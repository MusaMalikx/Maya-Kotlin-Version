package com.example.maya.Ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.maya.R
import com.example.maya.Ui.Libs.Firebase


class LoginActivity: AppCompatActivity(),View.OnClickListener {

    lateinit var email: EditText
    lateinit var password: EditText

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialog(this)

        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)

        val login_btn = findViewById<Button>(R.id.login_btn)
        login_btn.setOnClickListener(this)
        val register_click = findViewById<TextView>(R.id.register_click)
        register_click.setOnClickListener(this)
    }

    fun registerClick(){
        val myIntent = Intent(this, RegisterActivity::class.java)
        startActivity(myIntent)
        finish()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login_btn -> { textVerification() }
            R.id.register_click -> { registerClick() }
            else -> {}
        }
    }

    private fun textVerification(){
        if (email.text.isEmpty()){
            Toast.makeText(this,"Email can't empty!", Toast.LENGTH_SHORT).show()
            return
        }
        if(password.text.isEmpty()){
            Toast.makeText(this,"Password can't empty!", Toast.LENGTH_SHORT).show()
            return
        }

        signing()
    }

    private fun signing(){
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Signing Account")
        progressDialog.show()

        Firebase.firebaseAuth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    progressDialog.setMessage("Logged in User Data")
                    Toast.makeText(this, Firebase.firebaseAuth.currentUser!!.uid, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    progressDialog.dismiss()
                    finish()

                } else {
                    progressDialog.dismiss()
                    Log.w("EmailPassword", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, task.exception?.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}