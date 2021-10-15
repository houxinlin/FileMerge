package com.hxl.fm.extent

class StringExtent {
    companion object {
        fun String.addPrefix(prefix: String): String {
            if (this.startsWith(prefix)) {
                return this;
            }
            return prefix + this;
        }
    }
}