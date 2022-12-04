package com.example.maya.Ui

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.provider.Settings

class MediaService : Service() {

    private lateinit var player: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        player = MediaPlayer.create(this, Uri.parse("android.resource://com.example.maya/raw/_055"))

        player.setLooping(true)
        player.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }

}