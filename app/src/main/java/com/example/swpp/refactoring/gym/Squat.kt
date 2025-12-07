package com.example.swpp.refactoring.gym

class Squat(private val calPerRep: Float) : BodyWeightExercise() {

    init {
        name = "squat"
    }

    override fun caloriesPerRep(): Float {
        return calPerRep;
    }
}