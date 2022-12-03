package com.example.maya.Ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.maya.R
import com.example.maya.Ui.MediaService

class MusicFragment: Fragment() {

    lateinit var animationView: LottieAnimationView
    lateinit var animationMusicView: LottieAnimationView
    lateinit var animationPlayView: LottieAnimationView
    lateinit var musicLayout: LinearLayout
    lateinit var playBtn: Button
    lateinit var stopBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)

        animationView = view.findViewById(R.id.animationView)
        animationMusicView = view.findViewById(R.id.animationMusic)
        animationPlayView = view.findViewById(R.id.animationPlay)
        musicLayout = view.findViewById(R.id.music_layout)
        playBtn = view.findViewById(R.id.btn_play)
        stopBtn = view.findViewById(R.id.btn_stop)

        println()

        hideLayout()

        Handler().postDelayed({
            showLayout()
        }, 2000)

        playBtn.setOnClickListener {
            handlePlayClick()
        }

        stopBtn.setOnClickListener {
            handleStopClick()
        }

        if (bool == true){
            animationPlayView.pauseAnimation()
            animationPlayView.visibility = View.GONE
            animationMusicView.visibility = View.VISIBLE
            animationMusicView.playAnimation()
            animationMusicView.loop(true)
            playBtn.visibility = View.GONE
            stopBtn.visibility = View.VISIBLE
        }

        return view
    }

    private fun handlePlayClick(){
        animationPlayView.pauseAnimation()
        animationPlayView.visibility = View.GONE
        animationMusicView.visibility = View.VISIBLE
        animationMusicView.playAnimation()
        animationMusicView.loop(true)
        activity?.startService(Intent(view?.context, MediaService::class.java))
        playBtn.visibility = View.GONE
        stopBtn.visibility = View.VISIBLE
        bool = true
    }

    private fun handleStopClick(){
        animationMusicView.pauseAnimation()
        animationMusicView.visibility = View.GONE
        animationPlayView.visibility = View.VISIBLE
        animationPlayView.playAnimation()
        animationPlayView.loop(true)
        activity?.stopService(Intent(view?.context, MediaService::class.java))
        playBtn.visibility = View.VISIBLE
        stopBtn.visibility = View.GONE
        bool = false
    }

    private fun hideLayout(){
        animationView.playAnimation()
        animationView.loop(true)
        animationView.visibility = View.VISIBLE
        musicLayout.visibility = View.GONE
    }
    private fun showLayout(){
        animationView.pauseAnimation()
        animationView.visibility = View.GONE
        animationPlayView.playAnimation()
        animationPlayView.loop(true)
        musicLayout.visibility = View.VISIBLE
    }

    companion object {
        var bool: Boolean = false
    }
}