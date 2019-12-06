package com.efedaniel.csc514.tutorialseven

import android.content.Context
import android.opengl.GLSurfaceView
import android.opengl.GLU
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import com.efedaniel.csc514.tutorialseven.shapes.Cube

class MyGLRenderer(private val context: Context): GLSurfaceView.Renderer {

    private val cube: Cube = Cube()

    //Rotational Angle and speed
    private var angleCube = 0f
    private val speedCube = -1.5f

    // For controlling cube's z-position, x and y angles and speeds (NEW)
    var angleX = 0f   // (NEW)
    var angleY = 0f   // (NEW)
    var speedX = 0f   // (NEW)
    var speedY = 0f   // (NEW)
    var z = -6.0f    // (NEW)

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        gl?.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        gl?.glClearDepthf(1.0f)
        gl?.glEnable(GL10.GL_DEPTH_TEST)
        gl?.glDepthFunc(GL10.GL_LEQUAL)
        gl?.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
        gl?.glShadeModel(GL10.GL_SMOOTH)
        gl?.glDisable(GL10.GL_DITHER)

        // You OpenGL|ES initialization code here
        cube.loadTexture(gl!!, context)    // Load image into Texture (NEW)
        gl.glEnable(GL10.GL_TEXTURE_2D)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        val aspect = width.toFloat() / height.toFloat()
        gl!!.glViewport(0, 0, width, height)
        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()
        GLU.gluPerspective(gl, 45f, aspect, 0.1f, 100.0f)
        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()

        // You OpenGL|ES display re-sizing code here
    }

    override fun onDrawFrame(gl: GL10?) {

        gl!!.glClear(GL10.GL_COLOR_BUFFER_BIT)
        gl.glClear(GL10.GL_DEPTH_BUFFER_BIT)

        // Your OpenGL|ES rendering code here
        gl.glLoadIdentity()
        gl.glTranslatef(0.0f, 1.0f, z)
        gl.glScalef(0.6f, 0.6f, 0.8f)
        gl.glRotatef(angleX, 1.0f, 0.0f, 0.0f)
        gl.glRotatef(angleY, 0.0f, 1.0f, 0.0f)
        cube.draw(gl)
        angleX += speedX;  // (NEW)
        angleY += speedY

    }
}