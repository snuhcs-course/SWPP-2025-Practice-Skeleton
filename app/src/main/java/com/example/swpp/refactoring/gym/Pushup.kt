package com.example.swpp.refactoring.gym


class Pushup(private val calPerRep: Float) : Exercise() {

    init {
        name = "push-up"
    }

    fun requiresWeights(): Boolean {
        return false
    }

    override fun doExercise() {
        println("Wow, I totally just did a $name")
    }

    override fun caloriesPerRep(): Float {
        return calPerRep;
    }
}