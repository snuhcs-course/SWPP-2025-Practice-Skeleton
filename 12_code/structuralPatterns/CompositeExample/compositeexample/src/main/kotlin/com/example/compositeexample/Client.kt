package com.example.compositeexample

fun main(args: Array<String>) {
    val file1 = File("File1.txt")
    val file2 = File("File2.txt")
    val directory1 = Directory("Directory1")

    directory1.addFile(file1)
    directory1.addFile(file2)

    val file3 = File("File3.txt")
    val directory2 = Directory("Directory2")
    directory2.addFile(file3)
    directory2.addDirectory(directory1)

    directory2.showDetails()
}
