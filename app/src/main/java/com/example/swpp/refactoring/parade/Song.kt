package com.example.swpp.refactoring.parade

data class Song(val name: String, val artist: String, val popularity: Int) : Comparable<Song> {
    override fun compareTo(other: Song): Int = other.popularity.compareTo(this.popularity)
    override fun toString(): String = "$artist - $name"
}