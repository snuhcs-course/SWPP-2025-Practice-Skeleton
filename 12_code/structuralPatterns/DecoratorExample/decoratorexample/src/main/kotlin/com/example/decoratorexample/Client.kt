package com.example.decoratorexample

fun main(args: Array<String>) {
    val simpleMessage: Message = SimpleMessage("Hello")
    println(simpleMessage.getContent())

    val encryptedMessage: Message = EncryptedMessage("Hello")
    println(encryptedMessage.getContent())

    val compressedMessage: Message = CompressedMessage("Hello")
    println(compressedMessage.getContent())

    val encryptedAndCompressedMessage: Message = EncryptedAndCompressedMessage("Hello")
    println(encryptedAndCompressedMessage.getContent())
}
