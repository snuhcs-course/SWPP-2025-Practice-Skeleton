package com.example.swpp.refactoring

import com.example.swpp.refactoring.gym.Benchpress
import com.example.swpp.refactoring.gym.Deadlift
import com.example.swpp.refactoring.gym.Pushup
import com.example.swpp.refactoring.gym.Squat
import org.junit.Assert.*
import org.junit.Test

class GymTest {

    // Bench press
    @Test
    fun benchpressShouldRequireWeights() {
        val benchpress = Benchpress(100, 0.1f)
        assertTrue("Benchpress should require weights", benchpress.requiresWeights())
    }
    @Test
    fun benchpressWeightIsCorrect() {
        val weight = 100
        val calPerRep = 0.1f
        val benchpress = Benchpress(weight, calPerRep)
        assertEquals("Benchpress weight should be 100", weight, benchpress.getWeight())
    }
    @Test
    fun benchpressCalBurnedIsCorrect(){
        val weight = 100
        val calPerRep = 0.1f
        val benchpress = Benchpress(weight, calPerRep)
        benchpress.doExercise()
        assertEquals("One bench press with 100kg burns 10 kcals", weight * calPerRep, benchpress.caloriesBurned())
    }

    // Deadlift
    @Test
    fun deadliftShouldRequireWeights() {
        val deadlift = Deadlift(200, 0.2f)
        assertTrue("Deadlift should require weights", deadlift.requiresWeights())
    }
    @Test
    fun deadliftWeightIsCorrect() {
        val weight = 200
        val calPerRep = 0.2f
        val deadlift = Deadlift(weight, calPerRep)
        assertEquals("Deadlift weight should be 200", weight, deadlift.getWeight())
    }
    @Test
    fun deadliftCalBurnedIsCorrect(){
        val weight = 200
        val calPerRep = 0.2f
        val deadlift = Deadlift(weight, calPerRep)
        deadlift.doExercise()
        assertEquals("One bench press with 100kg burns 10 kcals", weight * calPerRep, deadlift.caloriesBurned())
    }

    // Push-up
    @Test
    fun pushupShouldNotRequireWeights() {
        val calPerRep = 0.5f
        val pushup = Pushup(calPerRep)
        assertFalse("Pushup should not require weights", pushup.requiresWeights())
    }
    @Test
    fun pushupCalBurnedIsCorrect(){
        val calPerRep = 0.5f
        val pushup = Pushup(calPerRep)
        pushup.doExercise()
        assertEquals("One bench press with 100kg burns 10 kcals", calPerRep, pushup.caloriesBurned())
    }

    // Squat
    @Test
    fun squatShouldNotRequireWeights() {
        val calPerRep = 1.5f
        val squat = Squat(calPerRep)
        assertFalse("Squat should not require weights", squat.requiresWeights())
    }
    @Test
    fun squatCalBurnedIsCorrect(){
        val calPerRep = 1.5f
        val squat = Squat(calPerRep)
        squat.doExercise()
        assertEquals("One bench press with 100kg burns 10 kcals", calPerRep, squat.caloriesBurned())
    }
}
