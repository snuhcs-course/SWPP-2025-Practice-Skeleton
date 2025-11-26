package com.example.swpp.refactoring.gym


class Deadlift(var weight: Int, private val calPerRep: Float) : Exercise() {

    init {
        name = "deadlift"
    }

    fun requiresWeights(): Boolean {
        return true
    }

    override fun caloriesPerRep(): Float {
        return weight * calPerRep;
    }
}