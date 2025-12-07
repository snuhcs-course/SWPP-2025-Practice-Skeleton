package com.example.swpp.refactoring.parade

object Lingo {
    private val beers = listOf(
        Beer("Cuvée des Trolls", 8),
        Beer("Lupulus", 9),
        Beer("Kwak", 10),
        Beer("Punk IPA", 7),
        Beer("Gurten", 5),
        Beer("Wittekop", 4),
        Beer("Brooklyn Lager", 6),
        Beer("CoCo POW!", 0)
    )
    private val topSongList = TopList(beers, 5)

    // returns the top N most popular beers
    fun getMostPopularBeers(): List<Beer> {
        return topSongList.getTop()
    }

    // prints the top N most popular beers in a nice way
    fun prettyPrintMostPopularBeers() {
        topSongList.prettyPrint("====TOP BEERS====")
    }

    // Other sample methods
    // getOpeningHours()
    // getMostPopularNonAlcoholicBeverages()
}