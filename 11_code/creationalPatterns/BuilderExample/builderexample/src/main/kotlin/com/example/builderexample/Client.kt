package com.example.builderexample

fun main(args: Array<String>) {
    val trainer = ModelTrainer(
        "Yolov5",
        "TrainDataloader", "TestDataloader",
        "SGD", "MSE",
        0.001, 64, 256
    )
    trainer.info()
}
