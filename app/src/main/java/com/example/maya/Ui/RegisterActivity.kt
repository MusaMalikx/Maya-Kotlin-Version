package com.example.maya.Ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import com.example.maya.R
import com.example.maya.Ui.Libs.Firebase

class RegisterActivity: AppCompatActivity() {

    private lateinit var registerButton: Button
    lateinit var progressDialog: ProgressDialog

    lateinit var fullname:EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerButton = findViewById(R.id.register_btn)
        val login_click = findViewById<TextView>(R.id.login_click)

        progressDialog = ProgressDialog(this)

        fullname = findViewById(R.id.register_fullname)
        email = findViewById(R.id.register_email)
        password = findViewById(R.id.register_password)
        confirmPassword = findViewById(R.id.register_confirm_password)

        registerButton.setOnClickListener {
            textVerification()
        }

        login_click.setOnClickListener {
            loginClick()
        }
    }

    private fun textVerification(){
        if (fullname.text.isEmpty()){
            Toast.makeText(this,"Name can't empty!", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.text.isEmpty()){
            Toast.makeText(this,"Email can't empty!", Toast.LENGTH_SHORT).show()
            return
        }

        if (!email.text.matches(emailPattern.toRegex())) {
            Toast.makeText(this,"Enter Valid Email", Toast.LENGTH_SHORT).show()
            return
        }
        if(password.text.isEmpty()){
            Toast.makeText(this,"Password can't empty!", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.text.toString() != confirmPassword.text.toString()){
            Toast.makeText(this,"Password not Match", Toast.LENGTH_SHORT).show()
            return
        }

        registering()
    }

    private fun registering(){

        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating Account")
        progressDialog.show()

        Firebase.firebaseAuth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    progressDialog.setMessage("Save User Data")
                   val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    progressDialog.dismiss()
                    Log.w("EmailPassword", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, task.exception?.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginClick(){
        val myIntent = Intent(this, LoginActivity::class.java)
        startActivity(myIntent)
    }

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

}