package com.hxl.fm

import com.hxl.fm.pk.FileTable

class Main {
}

fun main() {
    var dir = "/home/HouXinLin/project/html/";
    FileTable(dir).generatorForDirector()
    var decode = FileTable(dir).decode()

    decode?.decompression("/home/HouXinLin/project/test")

}