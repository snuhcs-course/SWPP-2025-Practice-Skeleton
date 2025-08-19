package com.example.creationalpattern

class NetworkLogger {
    fun error(msg: String) {
        // In practical, the below line is implemented using networking
        println("Error '$msg' to another server.")
    }
    
    fun warn(msg: String) {
        println("Warn '$msg' to another server.")
    }
    
    fun info(msg: String) {
        println("Info '$msg' to another server.")
    }
    
    fun debug(msg: String) {
        println("Debug '$msg' to another server.")
    }
}
