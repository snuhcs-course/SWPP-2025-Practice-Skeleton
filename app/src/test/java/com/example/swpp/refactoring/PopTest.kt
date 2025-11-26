package com.example.swpp.refactoring

import com.example.swpp.refactoring.parade.Hitparade
import com.example.swpp.refactoring.parade.Lingo
import org.junit.Assert.*
import org.junit.Test

class PopTest {
    // SongParade
    @Test
    fun testGetMostPopularSongsReturnsCorrectNumber() {
        val topSongs = Hitparade.getMostPopularSongs()
        assertEquals("Should return top 3 songs", 3, topSongs.size)
    }
    @Test
    fun testGetMostPopularSongsReturnsMostPopular() {
        val topSongs = Hitparade.getMostPopularSongs()
        assertEquals("Africa", topSongs[0].name, "First should be 'Africa' by Toto")
        assertEquals("Never Gonna Give You Up", topSongs[1].name, "Second should be 'Never Gonna Give You Up' by Rick Astley")
        assertEquals("Take On Me", topSongs[2].name, "Third should be 'Take On Me' by A-ha")
    }

    // BeerParade
    @Test
    fun testGetMostPopularBeersReturnsCorrectNumber() {
        val topBeers = Lingo.getMostPopularBeers()
        assertEquals("Should return top 5 beers", 5, topBeers.size)
    }
    @Test
    fun testGetMostPopularBeersReturnsMostPopular() {
        val topBeers = Lingo.getMostPopularBeers()
        assertEquals("Kwak", topBeers[0].name, "First should be 'Kwak'")
        assertEquals("Lupulus", topBeers[1].name, "Second should be 'Lupulus'")
    }
}