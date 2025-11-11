package com.example.decoratorexample

class EncryptedAndCompressedMessage(content: String) : Message {
    private val content: String

    init {
        this.content = compress(encrypt(content))
    }

    private fun encrypt(content: String): String {
        return "encrypted($content)"
    }

    private fun compress(content: String): String {
        return "compressed($content)"
    }

    override fun getContent(): String {
        return content
    }
}
