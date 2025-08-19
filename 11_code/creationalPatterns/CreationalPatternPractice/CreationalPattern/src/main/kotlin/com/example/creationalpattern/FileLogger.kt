package com.example.creationalpattern

class FileLogger {
    fun error(msg: String) {
        // In practical, the below line is implemented using file I/O
        println("Error '$msg' to file.")
    }
    
    fun warn(msg: String) {
        println("Warn '$msg' to file.")
    }
    
    fun info(msg: String) {
        println("Info '$msg' to file.")
    }
    
    fun debug(msg: String) {
        println("Debug'$msg' to file.")
    }
}
