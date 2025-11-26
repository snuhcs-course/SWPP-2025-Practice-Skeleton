package com.example.swpp.refactoring.gym


open class Exercise {

    var name: String = ""
    var count: Int = 0

    open fun doExercise() {
        println("Wow, I totally just did a $name")
        count++
    }

    open fun caloriesPerRep(): Float{
        return 1f
    }

    open fun caloriesBurned(): Float{
        return count * caloriesPerRep();
    }
}