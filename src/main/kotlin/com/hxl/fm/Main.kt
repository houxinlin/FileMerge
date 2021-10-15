package com.hxl.fm

import com.hxl.fm.extent.toByteArray
import com.hxl.fm.extent.toStringNoSplit
import com.hxl.fm.utils.FileUtils
import java.io.File

class Main {
}

fun main() {
    var a="/home/HouXinLin/res/";
    DirectorMerge(a).generator();
    var decode = DirectorMerge(a).decode()

    println(decode?.fileSize)
//    println((1).toByteArray())
//    var listDir = FileUtils.listDirDepth("");

//    println(listDir.toStringNoSplit().encodeToByteArray().size)

}