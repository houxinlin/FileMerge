package com.hxl.fm.pk

import com.hxl.fm.extent.toByteArray
import com.hxl.fm.io.ByteArrayIO
import com.hxl.fm.pk.FileTable.Companion.targetName
import com.hxl.fm.utils.FileUtils
import java.io.File
import java.nio.ByteBuffer

class GeneratorDirector(var targetDirector: String) {
    /**
     * 文件内容表
     */
    lateinit var fileTable: FileTable;


    lateinit var readBytes: ByteArray;
    fun generator() {
        var file = File(targetDirector, targetName)
        file.delete()

        var byteArrayIO = ByteArrayIO()
        byteArrayIO.writeInt(FileTable.FILE_BEGIN)
        var listDir = FileUtils.listDirDepth(targetDirector);
        byteArrayIO.writeByte(listDir.size.toByteArray())
        listDir.forEach { it ->
            val fileNameLength = it.toByteArray().size;
            var fileByteArray = File(targetDirector, it).readBytes()
            byteArrayIO.writeByte(fileNameLength)
            byteArrayIO.writeByte(it.toByteArray())
            byteArrayIO.writeInt((fileByteArray.size))
            byteArrayIO.writeByte(fileByteArray);
        }

        file.writeBytes(byteArrayIO.get())
    }

    fun decode(): FileTable? {
        var file = File(targetDirector, targetName)
        readBytes = file.readBytes()
        var byteArray = ByteBuffer.allocate(readBytes.size)
        byteArray.put(readBytes)
        byteArray.flip()
        var begin = byteArray.readInt();

        if (begin != FileTable.FILE_BEGIN) {
            print("解析出错");
            return null;
        }

        fileTable = FileTable(targetDirector);
        fileTable.fileSize = byteArray.readInt();

        for (i in 0 until fileTable.fileSize) {
            var fileNameSize = byteArray.readByte()
            var fileName = byteArray.readByte(fileNameSize.toInt()).decodeToString()
            var fileSize = byteArray.readInt();
            var fileBody = byteArray.readByte(fileSize);
            fileTable.fileContainer.put(fileName, FileContainer(fileName, fileBody))
        }
        return fileTable;
    }

    fun ByteBuffer.readByte(): Byte {
        return this.get()
    }

    fun ByteBuffer.readByte(size: Int): ByteArray {
        var byteArray = ByteArray(size)
        this.get(byteArray);
        return byteArray;
    }

    fun ByteBuffer.readInt(): Int {
        return this.getInt()
    }
}