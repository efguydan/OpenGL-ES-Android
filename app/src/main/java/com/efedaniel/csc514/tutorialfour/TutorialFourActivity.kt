package com.efedaniel.csc514.tutorialfour

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TutorialFourActivity : AppCompatActivity() {

    private lateinit var glView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glView = GLSurfaceView(this)
        glView.setRenderer(MyGLRenderer(this))
        setContentView(glView)
    }

    override fun onPause() {
        super.onPause()
        glView.onPause()
    }

    override fun onResume() {
        super.onResume()
        glView.onResume()
    }
}
