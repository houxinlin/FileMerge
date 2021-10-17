package com.hxl.fm.pk

import com.hxl.fm.extent.StringExtent.Companion.addPrefix
import com.hxl.fm.extent.toByteArray
import com.hxl.fm.io.ByteArrayIO
import com.hxl.fm.utils.FileUtils
import com.hxl.fm.utils.FileUtils.Companion.isExistsInSystem
import java.io.File

/**
 *FILE_BEGIN
 * 4个字节的文件数量
 *
 *List{
 *  1个字节的文件名称长度
 *  文件名
 *  4个字节的文件内容长度
 *  文件内容
 * }
 *
 */
class FileTable(var targetDirector: String) {


    companion object {
        /**
         * 目标文件名
         */
        public const val targetName = "target.wp";

        /**
         * 文件开头的4个字节，必须是255495
         */
        const val FILE_BEGIN: Int = 255495;
    }

    var fileSize: Int = 0;

    /**
     * 文件集合String->FileContainer
     */
    var fileContainer = mutableMapOf<String, FileContainer>();


    fun generatorForDirector() {
        return GeneratorDirector(targetDirector).generator()
    }

    fun decode(): FileTable? {
        return GeneratorDirector(targetDirector).decode()
    }

    private fun save() {
        GeneratorMap(targetDirector).generator(fileContainer)
    }

    fun get(name: String): ByteArray? {
        var newName = (name.addPrefix("/"))
        return fileContainer.getOrDefault(newName, null)?.file;
    }

    fun listFiles(): MutableSet<String> {
        return fileContainer.keys;
    }


    fun addFile(filePath: String): Boolean {
        if (filePath.isExistsInSystem()) {
            return addFile(File(filePath))
        }
        return false;

    }

    fun addFile(file: File): Boolean {
        if (!fileContainer.containsKey(file.name)) {
            fileContainer[file.name] = FileContainer(file.name, file.readBytes())
            save()
            return true;
        }
        return false;
    }

    fun addFile(name: String, data: ByteArray): Boolean {
        if (!fileContainer.containsKey(name)) {
            fileContainer[name] = FileContainer(name, data)
            save()
            return true;
        }
        return false;
    }

    /**
     * 解压
     */
    fun decompression(filePath: String) {
        var keys = fileContainer.keys
        for (key in keys) {
            var substring = key.substring(0, key.lastIndexOf("/"))
            if (substring.isNotEmpty()) {
                println(substring)
                FileUtils.createDirector(filePath, substring)
                FileUtils.writeBytes(filePath, key,fileContainer[key]!!.file)

            }
        }
    }
}