package com.example.decoratorexample

class CompressedMessage(content: String) : Message {
    private val content: String

    init {
        this.content = compress(content)
    }

    private fun compress(content: String): String {
        return "compressed($content)"
    }

    override fun getContent(): String {
        return content
    }
}
