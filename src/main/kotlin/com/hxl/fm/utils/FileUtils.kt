package com.hxl.fm.utils

import com.hxl.fm.extent.StringExtent.Companion.addPrefix
import java.io.File

class FileUtils {
    companion object {
        fun listDir(path: String): List<String> {
            if (path.isExistsInSystem()) {
                return File(path).list().asList();
            }
            return emptyList();
        }

        fun listDirDepth(path: String): List<String> {
            return listDirDepthAndDeletePrefix(path, path);
        }

        private fun listDirDepthAndDeletePrefix(path: String, prefix: String): List<String> {
            var fileList = mutableListOf<String>();
            if (path.isExistsInSystem()) {
                var list = File(path).listFiles()
                list.forEach {
                    if (it.isFile) {
                        if (it.isFile) {
                            fileList.add((path + "/" + it.name).removePrefix(prefix).addPrefix("/"));
                        }
                    }
                    if (it.isDirectory) {
                        fileList.addAll(listDirDepthAndDeletePrefix(path + "/" + it.name, prefix))
                    }
                }

            }
            return fileList;
        }


        fun String.isExistsInSystem(): Boolean {
            return File(this).isDirectory
        }
    }

}