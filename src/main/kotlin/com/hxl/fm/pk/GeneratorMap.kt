package com.hxl.fm.pk

import com.hxl.fm.extent.toByteArray
import com.hxl.fm.io.ByteArrayIO
import com.hxl.fm.pk.FileTable.Companion.targetName
import java.io.File

class GeneratorMap(var targetDirector: String) {
    fun generator(files: Map<String, FileContainer>) {
        var file = File(targetDirector, targetName)
        file.delete()

        var byteArrayIO = ByteArrayIO()
        byteArrayIO.writeInt(FileTable.FILE_BEGIN)
        var listDir = files.keys;
        byteArrayIO.writeByte(listDir.size.toByteArray())
        listDir.forEach { it ->
            val fileNameLength = it.toByteArray().size;
            byteArrayIO.writeByte(fileNameLength)
            byteArrayIO.writeByte(it.toByteArray())
            byteArrayIO.writeInt((files[it]!!.file!!.size))
            byteArrayIO.writeByte(files[it]!!.file);
        }

        file.writeBytes(byteArrayIO.get())
    }
}