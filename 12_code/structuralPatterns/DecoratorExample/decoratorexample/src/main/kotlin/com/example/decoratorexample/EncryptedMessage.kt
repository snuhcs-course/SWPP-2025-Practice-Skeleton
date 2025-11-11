package com.example.decoratorexample

class EncryptedMessage(content: String) : Message {
    private val content: String

    init {
        this.content = encrypt(content)
    }

    private fun encrypt(content: String): String {
        return "encrypted($content)"
    }

    override fun getContent(): String {
        return content
    }
}
