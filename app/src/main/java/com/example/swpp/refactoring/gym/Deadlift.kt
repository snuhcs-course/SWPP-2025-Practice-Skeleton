package com.example.swpp.refactoring.gym


class Deadlift(private val weight: Int, private val calPerRep: Float) : Exercise() {

    init {
        name = "deadlift"
    }

    fun requiresWeights(): Boolean {
        return true
    }

    fun getWeight(): Int {
        return weight
    }

    override fun doExercise() {
        println("Wow, I totally just did a $name")
    }

    override fun caloriesPerRep(): Float {
        return weight * calPerRep;
    }
}