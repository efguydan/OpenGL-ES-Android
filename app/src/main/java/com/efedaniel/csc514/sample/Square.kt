package com.efedaniel.csc514.sample

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class Square {

    private var vertexBuffer: FloatBuffer

    val vertices = floatArrayOf(
        -1.0f, -1.0f,  0.0f,  // 0. left-bottom
        1.0f, -1.0f,  0.0f,  // 1. right-bottom
        -1.0f,  1.0f,  0.0f,  // 2. left-top
        1.0f,  1.0f,  0.0f   // 3. right-top
    )

    init {
        // Setup vertex array buffer. Vertices in float. A float has 4 bytes
        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)
    }
}