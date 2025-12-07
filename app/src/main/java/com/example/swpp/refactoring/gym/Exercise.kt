package com.example.swpp.refactoring.gym


abstract class Exercise {

    var name: String = ""
    var count: Int = 0

    abstract fun caloriesPerRep(): Float

    abstract fun requiresWeights(): Boolean

    open fun doExercise() {
        println("Wow, I totally just did a $name")
        count++
    }

    open fun caloriesBurned(): Float{
        return count * caloriesPerRep();
    }


}