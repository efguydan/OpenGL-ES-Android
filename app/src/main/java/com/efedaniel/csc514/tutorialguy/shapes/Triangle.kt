package com.efedaniel.csc514.tutorialguy.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class Triangle {

    private var vertexBuffer: FloatBuffer
    private var colorBuffer: FloatBuffer
    private var indexBuffer: ByteBuffer

    val vertices = floatArrayOf(
        0.0f,  1.0f, 0.0f, // 0. top
        -1.0f, -1.0f, 0.0f, // 1. left-bottom
        1.0f, -1.0f, 0.0f  // 2. right-bottom
    )

    val indices = byteArrayOf(
        0, 1, 2
    )

    val colors = floatArrayOf(
        1.0f, 0.0f, 0.0f, 1.0f, // Red (NEW)
        0.0f, 1.0f, 0.0f, 1.0f, // Green (NEW)
        0.0f, 0.0f, 1.0f, 1.0f  // Blue (NEW)
    )

    init {
        // Setup vertex-array buffer. Vertices in float. A float has 4 bytes.
        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        // Setup color-array buffer. Colors in float. A float has 4 bytes (NEW)
        val cbb = ByteBuffer.allocateDirect(colors.size * 4)
        cbb.order(ByteOrder.nativeOrder())
        colorBuffer = cbb.asFloatBuffer()
        colorBuffer.put(colors)
        colorBuffer.position(0)

        // Setup index-array buffer. Indices in byte.
        indexBuffer = ByteBuffer.allocateDirect(indices.size)
        indexBuffer.put(indices)
        indexBuffer.position(0)
    }

    fun draw(gl: GL10) {
        // Enable vertex-array and define the buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)

        // Enable color-array and define the buffers
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer)

        // Draw the primitives via index-array
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.size, GL10.GL_UNSIGNED_BYTE, indexBuffer)

        //Disable the vertex array and the color array
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY)

    }

}