package com.efedaniel.csc514.tutorialguy.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class Square {

    private var vertexBuffer: FloatBuffer

    val vertices = floatArrayOf(
        -1.0f, -1.0f,  0.0f,  // 0. left-bottom
        1.0f, -1.0f,  0.0f,  // 1. right-bottom
        -1.0f,  1.0f,  0.0f,  // 2. left-top
        1.0f,  1.0f,  0.0f   // 3. right-top
    )

    init {
        // Setup vertex-array buffer. Vertices in float. A float has 4 bytes.
        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)
    }

    fun draw(gl: GL10) {
        // Enable vertex-array and define the buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)

        //Set the color
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f)

        // Draw the primitives via index-array
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.size / 3)
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)

    }

}