package com.example.observerexample

class Display {
    private var temperature: Int = 0
    private var humidity: Int = 0
    private var pressure: Int = 0

    fun update(temperature: Int, humidity: Int, pressure: Int) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        display()
    }

    private fun display() {
        println("Current conditions: $temperature F degrees and $humidity% humidity $pressure pressure")
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
