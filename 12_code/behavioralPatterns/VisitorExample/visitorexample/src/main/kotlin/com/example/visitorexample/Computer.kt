package com.example.visitorexample

class Computer : ComputerPart {
    private val parts: Array<ComputerPart>

    init {
        parts = arrayOf(CPU(), GPU(), RAM())
    }

    override fun run() {
        for (i in parts.indices) {
            parts[i].run()
        }
        println("Running Computer")
    }
}
