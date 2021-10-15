package com.hxl.fm.io

import com.hxl.fm.extent.toByteArray
import java.io.ByteArrayOutputStream

class ByteArrayIO {
    /**
     * 输出字节暂存放区域
     */
    var byteBuffer = ByteArrayOutputStream();


    fun writeInt(value: Int) {
        byteBuffer.write(value.toByteArray())
    }

    fun writeByte(value: ByteArray) {
        byteBuffer.write(value)
    }

    fun writeByte(value: Int) {
        byteBuffer.write(value);
    }

    fun get(): ByteArray {
        return byteBuffer.toByteArray()
    }
}