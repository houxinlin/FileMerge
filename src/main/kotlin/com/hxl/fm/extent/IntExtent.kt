package com.hxl.fm.extent

import java.nio.ByteBuffer
import java.nio.IntBuffer

class IntExtent {
}

fun Int.toByteArray(): ByteArray {
    var allocate = ByteBuffer.allocate(4)
    allocate.putInt(this);
    allocate.flip();
    return allocate.array()
}