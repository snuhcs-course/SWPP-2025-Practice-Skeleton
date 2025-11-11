package com.example.observerexample

fun main(args: Array<String>) {
    val weatherData = WeatherData()
    val display1 = Display()
    val display2 = Display()

    weatherData.setMeasurements()

    if (weatherData.getTemperature() != display1.getTemperature()) {
        if (weatherData.getHumidity() != display1.getHumidity()) {
            if (weatherData.getPressure() != display1.getPressure()) {
                display1.update(weatherData.getTemperature(), weatherData.getHumidity(), weatherData.getPressure())
            }
        }
    }

    if (weatherData.getTemperature() != display2.getTemperature()) {
        if (weatherData.getHumidity() != display2.getHumidity()) {
            if (weatherData.getPressure() != display2.getPressure()) {
                display2.update(weatherData.getTemperature(), weatherData.getHumidity(), weatherData.getPressure())
            }
        }
    }
    // Do this everytime
}
