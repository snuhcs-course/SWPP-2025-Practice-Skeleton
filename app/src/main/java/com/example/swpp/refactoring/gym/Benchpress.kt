package com.example.swpp.refactoring.gym


class Benchpress(weight: Int, private val calPerRep: Float) : WeightedExercise() {

    init {
        name = "bench press"
        this.weight = weight
    }

    override fun caloriesPerRep(): Float {
        return weight * calPerRep;
    }
}