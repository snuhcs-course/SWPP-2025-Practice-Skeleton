package com.example.creationalpattern

class MACLogger {
    fun error(msg: String) {
        println("Error '$msg' to console.")
    }
    
    fun warn(msg: String) {
        println("Warn '$msg' to console.")
    }
    
    fun info(msg: String) {
        println("Info '$msg' to console.")
    }
    
    fun debug(msg: String) {
        println("Debug '$msg' to console.")
    }
}
