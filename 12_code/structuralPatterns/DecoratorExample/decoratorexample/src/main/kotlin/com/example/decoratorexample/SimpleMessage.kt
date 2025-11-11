package com.example.decoratorexample

class SimpleMessage(private val content: String) : Message {
    override fun getContent(): String {
        return content
    }
}
