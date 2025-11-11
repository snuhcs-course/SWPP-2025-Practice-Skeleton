package com.example.compositeexample

class Directory(private val name: String) {
    private val files = ArrayList<File>()
    private val directories = ArrayList<Directory>()

    fun addFile(file: File) {
        files.add(file)
    }

    fun addDirectory(directory: Directory) {
        directories.add(directory)
    }

    fun showDetails() {
        println("Directory: $name")
        for (file in files) {
            file.showDetails()
        }
        for (directory in directories) {
            directory.showDetails()
        }
    }
}
