package com.hxl.fm.extent


fun <T> List<T>.toStringNoSplit(): String {
    var stringBuilder = StringBuilder()
    for (t in this) {
        stringBuilder.append(t.toString());
    }
    return stringBuilder.toString();
}