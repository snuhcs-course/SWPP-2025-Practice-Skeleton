package com.example.creationalpattern

object OperatingSystem {
    private const val type = "MAC"
    
    @JvmStatic
    fun main(args: Array<String>) {
        adder()
        multiplier()
    }

    private fun adder() {
        val loggerType = "File"
        val message = "log from adder"

        if (loggerType == "MAC") {
            val logger = MACLogger()
            logger.error(message)
        } else if (loggerType == "Android") {
            val logger = AndroidLogger()
            logger.error(message)
        } else if (loggerType == "File") {
            val logger = FileLogger()
            logger.error(message)
        } else if (loggerType == "Network") {
            val logger = NetworkLogger()
            logger.error(message)
        }
    }

    private fun multiplier() {
        val loggerType = "Android"
        val message = "log from multiplier"

        if (loggerType == "Mac") {
            val logger = MACLogger()
            logger.error(message)
        } else if (loggerType == "Android") {
            val logger = AndroidLogger()
            logger.error(message)
        } else if (loggerType == "File") {
            val logger = FileLogger()
            logger.error(message)
        } else if (loggerType == "Network") {
            val logger = NetworkLogger()
            logger.error(message)
        }
    }
}
