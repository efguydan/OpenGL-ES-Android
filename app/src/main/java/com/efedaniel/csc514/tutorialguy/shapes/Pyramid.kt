package com.efedaniel.csc514.tutorialguy.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class Pyramid {

    private var vertexBuffer: FloatBuffer
    private var colorBuffer: FloatBuffer
    private var indexBuffer: ByteBuffer

    val vertices = floatArrayOf(
        -1.0f, -1.0f, -1.0f,  // 0. left-bottom-back
        1.0f, -1.0f, -1.0f,  // 1. right-bottom-back
        1.0f, -1.0f,  1.0f,  // 2. right-bottom-front
        -1.0f, -1.0f,  1.0f,  // 3. left-bottom-front
        0.0f,  1.0f,  0.0f   // 4. top
    )

    val colors = floatArrayOf(
        0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
        0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 3. green
        1.0f, 0.0f, 0.0f, 1.0f   // 4. red
    )

    val indices = byteArrayOf(
        2, 4, 3,   // front face (CCW)
        1, 4, 2,   // right face
        0, 4, 1,   // back face
        4, 0, 3    // left face
    )

    init {
        // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        // Setup color-array buffer. Colors in float. An float has 4 bytes
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

    // Draw the shape
    fun draw(gl: GL10) {
        gl.glFrontFace(GL10.GL_CCW)  // Front face in counter-clockwise orientation

        // Enable arrays and define their buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer)

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.size, GL10.GL_UNSIGNED_BYTE, indexBuffer)

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY)

    }
}