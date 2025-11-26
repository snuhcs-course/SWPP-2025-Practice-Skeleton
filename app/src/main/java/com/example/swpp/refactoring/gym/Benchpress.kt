package com.example.swpp.refactoring.gym


class Benchpress(private val weight: Int, private val calPerRep: Float) : Exercise() {

    init {
        name = "bench press"
    }

    fun requiresWeights(): Boolean {
        return true
    }

    fun getWeight(): Int {
        return weight
    }


    override fun caloriesPerRep(): Float {
        return weight * calPerRep;
    }
}