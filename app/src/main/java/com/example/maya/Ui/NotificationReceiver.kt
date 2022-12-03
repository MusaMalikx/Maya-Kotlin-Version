package com.example.maya.Ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(ConnectivityManager.CONNECTIVITY_ACTION == intent?.action)
        {
            val boolExtra= intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false)
            if(!boolExtra)
            {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()

            }
        }
//        println("BroadCast Received!")
//        val tag = "Receiver"
//        Log.d("MessageReceiver", "intent=" + intent)
//        val message = intent?.getStringExtra("message")
//        Log.d(tag, message.toString())
    }
}