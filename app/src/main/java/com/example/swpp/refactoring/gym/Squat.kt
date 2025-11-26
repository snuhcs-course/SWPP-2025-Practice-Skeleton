package com.example.swpp.refactoring.gym

class Squat(private val calPerRep: Float) : Exercise() {

    init {
        name = "squat"
    }

    fun requiresWeights(): Boolean {
        return false
    }

    override fun caloriesPerRep(): Float {
        return calPerRep;
    }
}