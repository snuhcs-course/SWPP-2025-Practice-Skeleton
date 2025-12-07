package com.example.swpp.refactoring.parade

class TopList<T: Comparable<T>>(private val items: List<T>, private val N: Int) {
    fun getTop(): List<T> = items.sorted().take(N)
    fun prettyPrint(title: String){
        println(title)
        items.forEachIndexed { index, item ->
            println("${index + 1}) $item")
        }
    }
}