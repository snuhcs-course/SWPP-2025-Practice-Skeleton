package com.example.proxyexample

fun main(args: Array<String>) {
    val realService = RealService()
    realService.execute()
}
