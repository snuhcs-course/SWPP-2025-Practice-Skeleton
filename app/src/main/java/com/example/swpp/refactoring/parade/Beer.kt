package com.example.swpp.refactoring.parade

data class Beer(val name: String, val popularity: Int) : Comparable<Beer> {
    override fun compareTo(other: Beer): Int = other.popularity.compareTo(this.popularity)
    override fun toString(): String = name
}