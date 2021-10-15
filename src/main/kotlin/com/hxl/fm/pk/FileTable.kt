package com.hxl.fm.pk

import com.hxl.fm.extent.StringExtent.Companion.addPrefix

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
class FileTable {
    companion object {
        /**
         * 文件开头的4个字节，必须是255495
         */
        const val FILE_BEGIN: Int = 255495;
    }

    var fileSize: Int = 0;
//weibaba0422

    /**
     * 文件集合String->FileContainer
     */
    var fileContainer = mutableMapOf<String, FileContainer>();

    fun get(name: String): ByteArray? {
       var  newName = (name.addPrefix("/"))
        return fileContainer.getOrDefault(newName, null)?.file;
    }
}