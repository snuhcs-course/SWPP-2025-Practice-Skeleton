package com.example.swpp.refactoring.gym


class Deadlift(weight: Int, private val calPerRep: Float) : WeightedExercise() {

    init {
        name = "deadlift"
        this.weight = weight
    }

    override fun caloriesPerRep(): Float {
        return weight * calPerRep;
    }
}