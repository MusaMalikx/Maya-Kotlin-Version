package com.example.maya.Ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class Receivers: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?)
    {
        if(Intent.ACTION_BOOT_COMPLETED == p1?.action)
        {
            Toast.makeText(p0, "Boot Complete", Toast.LENGTH_SHORT).show()
        }
        else if(Intent.ACTION_BATTERY_CHANGED == p1?.action)
        {
            Toast.makeText(p0, "Battery Changed", Toast.LENGTH_SHORT).show()
        }
        else if(Intent.ACTION_SCREEN_ON == p1?.action)
        {
            Toast.makeText(p0, "Screen On", Toast.LENGTH_SHORT).show()
        }
        else if(ConnectivityManager.CONNECTIVITY_ACTION == p1?.action)
        {
            val boolExtra= p1.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false)
            if(!boolExtra)
            {
                Toast.makeText(p0, "Connected", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(p0, "Disconnected", Toast.LENGTH_SHORT).show()

            }
        }
    }
}