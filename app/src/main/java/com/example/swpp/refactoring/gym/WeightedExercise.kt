package com.example.swpp.refactoring.gym

abstract class WeightedExercise: Exercise() {
    var weight: Int = 0
        protected set

    override fun requiresWeights() = true
}