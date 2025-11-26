package com.example.swpp.refactoring.parade

object Hitparade {
    private val songs = listOf(
        Song("Never Gonna Give You Up", "Rick Astley", 8),
        Song("Friday", "Rebecca Black", 1),
        Song("CHIHUAHUA", "DJ BoBo", 5),
        Song("Bohemian Rhapsody", "Queen", 5),
        Song("Take On Me", "A-ha", 7),
        Song("Africa", "Toto", 10),
        Song("Gucci Gang", "Lil Pump", 5),
        Song("Gangnam Style", "PSY", 5)
    )
    private const val N = 3
    // returns the top N most popular songs
    fun getMostPopularSongs(): List<Song> {
        return songs.sorted().take(N)
    }

    // prints the top N most popular songs in a nice way
    fun prettyPrintMostPopularSongs() {
        val topSongs = getMostPopularSongs()
        println("====TOP SONGS====")
        topSongs.forEachIndexed { index, song ->
            println("${index + 1}) $song")
        }
    }
    // other methods like:
    // getMostTrendingArtists()
    // getTopSellingRockAlbum()
}
