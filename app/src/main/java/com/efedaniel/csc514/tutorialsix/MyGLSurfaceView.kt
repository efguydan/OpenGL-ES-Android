package com.efedaniel.csc514.tutorialsix

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.KeyEvent
import android.view.MotionEvent


class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private var renderer: MyGLRenderer = MyGLRenderer(context)

    private val TOUCH_SCALE_FACTOR = 180.0f / 320.0f
    private var previousX: Float = 0.0f
    private var previousY: Float = 0.0f

    init {
        setRenderer(renderer)
        requestFocus()
        isFocusableInTouchMode = true
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_I -> renderer.speedY -= 0.1f
            KeyEvent.KEYCODE_P -> renderer.speedY += 0.1f
            KeyEvent.KEYCODE_O -> renderer.speedX -= 0.1f
            KeyEvent.KEYCODE_K -> renderer.speedX += 0.1f
            KeyEvent.KEYCODE_A -> renderer.z -= 0.2f
            KeyEvent.KEYCODE_Z -> renderer.z += 0.2f
        }
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val currentX = event!!.x
        val currentY = event.y
        var deltaX = 0.0f
        var deltaY = 0.0f
        when(event?.action) {
            MotionEvent.ACTION_MOVE -> {
                deltaX = currentX - previousX
                deltaY = currentY - previousY
                renderer.angleX += deltaY * TOUCH_SCALE_FACTOR
                renderer.angleY += deltaX * TOUCH_SCALE_FACTOR
            }
        }
        previousX = currentX
        previousY = currentY
        return true
    }

}