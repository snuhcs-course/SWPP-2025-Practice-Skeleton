package com.example.swpp.refactoring.gym


class Pushup(private val calPerRep: Float) : Exercise() {

    init {
        name = "push-up"
    }

    fun requiresWeights(): Boolean {
        return false
    }

    override fun caloriesPerRep(): Float {
        return calPerRep;
    }
}