package com.efedaniel.csc514.tutorialseven.shapes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLUtils
import com.efedaniel.csc514.R
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class Cube {

    private val vertexBuffer: FloatBuffer
    private val texBuffer: FloatBuffer

    private val vertices = floatArrayOf(// Vertices for a face at z=0
        -1.0f, -1.0f, 0.0f, // 0. left-bottom-front
        1.0f, -1.0f, 0.0f, // 1. right-bottom-front
        -1.0f, 1.0f, 0.0f, // 2. left-top-front
        1.0f, 1.0f, 0.0f   // 3. right-top-front
    )

    var texCoords = floatArrayOf(// Texture coords for the above face (NEW)
        0.0f, 1.0f, // A. left-bottom (NEW)
        1.0f, 1.0f, // B. right-bottom (NEW)
        0.0f, 0.0f, // C. left-top (NEW)
        1.0f, 0.0f   // D. right-top (NEW)
    )
    var textureIDs = IntArray(1)   // Array for 1 texture-ID (NEW)

    init {
        // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        // Setup texture-coords-array buffer, in float. An float has 4 bytes (NEW)
        val tbb = ByteBuffer.allocateDirect(texCoords.size * 4)
        tbb.order(ByteOrder.nativeOrder())
        texBuffer = tbb.asFloatBuffer()
        texBuffer.put(texCoords)
        texBuffer.position(0)
    }

    fun draw(gl: GL10) {
        gl.glFrontFace(GL10.GL_CCW)    // Front face in counter-clockwise orientation
        gl.glEnable(GL10.GL_CULL_FACE) // Enable cull face
        gl.glCullFace(GL10.GL_BACK)    // Cull the back face (don't display)

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY)  // Enable texture-coords-array (NEW)
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer) // Define texture-coords buffer (NEW)

        // front
        gl.glPushMatrix()
        gl.glTranslatef(0.0f, 0.0f, 1.0f)
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4)
        gl.glPopMatrix()

        // left
        gl.glPushMatrix()
        gl.glRotatef(270.0f, 0.0f, 1.0f, 0.0f)
        gl.glTranslatef(0.0f, 0.0f, 1.0f)
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4)
        gl.glPopMatrix()

        // back
        gl.glPushMatrix()
        gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f)
        gl.glTranslatef(0.0f, 0.0f, 1.0f)
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4)
        gl.glPopMatrix()

        // right
        gl.glPushMatrix()
        gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f)
        gl.glTranslatef(0.0f, 0.0f, 1.0f)
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4)
        gl.glPopMatrix()

        // top
        gl.glPushMatrix()
        gl.glRotatef(270.0f, 1.0f, 0.0f, 0.0f)
        gl.glTranslatef(0.0f, 0.0f, 1.0f)
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4)
        gl.glPopMatrix()

        // bottom
        gl.glPushMatrix()
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f)
        gl.glTranslatef(0.0f, 0.0f, 1.0f)
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4)
        gl.glPopMatrix()

        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY)
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisable(GL10.GL_CULL_FACE)
    }

    @SuppressLint("ResourceType")
    fun loadTexture(gl: GL10, context: Context) {
        gl.glGenTextures(1, textureIDs, 0)
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[0])

        // Set up texture filters
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST.toFloat())
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR.toFloat())

        // Construct an input stream to texture image "res\drawable\nehe.png"
        val istream = context.resources.openRawResource(R.drawable.oaulogo)
        val bitmap = try {
            BitmapFactory.decodeStream(istream)
        } finally {
            try {
                istream.close()
            } catch (e: IOException) { }
        }

        // Build Texture from loaded bitmap for the currently-bind texture ID
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0)
        bitmap.recycle()

    }

}