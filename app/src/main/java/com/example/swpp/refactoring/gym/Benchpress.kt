package com.example.swpp.refactoring.gym


class Benchpress(var weight: Int, private val calPerRep: Float) : Exercise() {

    init {
        name = "bench press"
    }

    fun requiresWeights(): Boolean {
        return true
    }

    override fun caloriesPerRep(): Float {
        return weight * calPerRep;
    }
}