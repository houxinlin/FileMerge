package com.hxl.fm

import com.hxl.fm.extent.toByteArray
import com.hxl.fm.extent.toStringNoSplit
import com.hxl.fm.pk.FileTable
import com.hxl.fm.utils.FileUtils
import java.io.File

class Main {
}

fun main() {
    var dir = "/home/HouXinLin/res/";
    FileTable(dir).generatorForDirector()
    var decode =FileTable(dir).decode()

    decode?.addFile("/home/HouXinLin/2021052501.data")
    println(decode?.listFiles())

}