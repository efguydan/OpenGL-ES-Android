package com.efedaniel.csc514.tutorialguy

import android.content.Context
import android.opengl.GLSurfaceView
import android.opengl.GLU
import android.opengl.Matrix
import com.efedaniel.csc514.tutorialguy.shapes.Square
import com.efedaniel.csc514.tutorialguy.shapes.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import com.efedaniel.csc514.tutorialguy.shapes.Cube
import com.efedaniel.csc514.tutorialguy.shapes.Pyramid



class MyGLRenderer(context: Context): GLSurfaceView.Renderer {

    private val triangle: Triangle = Triangle()
    private val square: Square = Square()
    private val pyramid: Pyramid = Pyramid()
    private val cube: Cube = Cube()

    //Rotational Angle and speed
    private var angleTriangle = 0.0f
    private var angleQuad = 0.0f
    private val speedTriangle = 0.5f
    private val speedQuad = -0.4f
    private var anglePyramid = 0f
    private var angleCube = 0f
    private val speedPyramid = 2.0f
    private val speedCube = -1.5f

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        gl?.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        gl?.glClearDepthf(1.0f)
        gl?.glEnable(GL10.GL_DEPTH_TEST)
        gl?.glDepthFunc(GL10.GL_LEQUAL)
        gl?.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
        gl?.glShadeModel(GL10.GL_SMOOTH)
        gl?.glDisable(GL10.GL_DITHER)

        // You OpenGL|ES initialization code here
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
        gl.glTranslatef(-1.5f, 0.0f, -6.0f)

        //Triangle
//        gl.glRotatef(angleTriangle, 0.0f, 1.0f, 0.0f) //Rotate Triangle about y-axis
//        triangle.draw(gl)

        //Pyramid
        gl.glRotatef(anglePyramid, 0.1f, 1.0f, -0.1f)
        pyramid.draw(gl)

        gl.glLoadIdentity();
        gl.glTranslatef(1.5f, 0.0f, -6.0f)

        //Square
//        gl.glRotatef(angleQuad, 1.0f, 0.0f, 0.0f) //Rotate Square about x-axis
//        square.draw(gl)

        //Cube
        gl.glScalef(0.8f, 0.8f, 0.8f)
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f)
        cube.draw(gl)

        angleTriangle += speedTriangle
        angleQuad += speedQuad
        anglePyramid += speedPyramid
        angleCube += speedCube

    }
}