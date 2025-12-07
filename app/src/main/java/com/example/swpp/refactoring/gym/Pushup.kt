package com.example.swpp.refactoring.gym


class Pushup(private val calPerRep: Float) : BodyWeightExercise() {

    init {
        name = "push-up"
    }

    override fun caloriesPerRep(): Float {
        return calPerRep;
    }
}