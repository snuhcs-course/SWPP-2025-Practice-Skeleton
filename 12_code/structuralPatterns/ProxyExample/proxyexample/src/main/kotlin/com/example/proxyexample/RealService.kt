package com.example.proxyexample

class RealService {
    fun execute() {
        println("Execution start")
        for (i in 0 until 10) {
            println(i * i)
        }
        println("Execution end")
    }
}
