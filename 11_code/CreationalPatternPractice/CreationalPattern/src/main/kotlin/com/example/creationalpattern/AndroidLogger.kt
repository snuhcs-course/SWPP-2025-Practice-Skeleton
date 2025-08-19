package com.example.creationalpattern

class AndroidLogger {
    fun error(msg: String) {
        // In practical, the below line is implemented using Toast or other logging tools
        println("Error '$msg' to Toast.")
    }
    
    fun warn(msg: String) {
        println("Warn '$msg' to Toast.")
    }
    
    fun info(msg: String) {
        println("Info '$msg' to Toast.")
    }
    
    fun debug(msg: String) {
        println("Debug '$msg' to Toast.")
    }
}
