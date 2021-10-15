# FileMerge

这是一个可以合并文件夹目录的工具，可以产生一个target.wp文件，其中内容包括文件夹中所有文件，可更具api从target.wp中提取指定文件。

# 示例

```java
fun main() {
    var dir="/home/HouXinLin/res/";
    DirectorMerge(dir).generator();
    var decode = DirectorMerge(dir).decode()
    println(decode.fileSize)
}
```
