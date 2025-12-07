package com.example.swpp.refactoring.gym

abstract class BodyWeightExercise: Exercise() {
    override fun requiresWeights() = false
}