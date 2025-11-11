package com.example.observerexample

import kotlin.random.Random

class WeatherData {
    private var temperature: Int = 0
    private var humidity: Int = 0
    private var pressure: Int = 0

    fun setMeasurements() {
        this.temperature = Random.nextInt(0, 100)
        this.humidity = Random.nextInt(0, 100)
        this.pressure = Random.nextInt(0, 100)
    }

    fun getTemperature(): Int {
        return temperature
    }

    fun getHumidity(): Int {
        return humidity
    }

    fun getPressure(): Int {
        return pressure
    }
}
