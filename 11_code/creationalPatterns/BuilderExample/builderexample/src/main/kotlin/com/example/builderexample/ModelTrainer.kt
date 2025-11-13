package com.example.builderexample

class ModelTrainer {
    var model: String
    var trainDataloader: String
    var validDataloader: String? = null
    var testDataloader: String
    var optimizer: String
    var lossFunction: String
    var learningRate: Double
    var preProcessor: String? = null
    var postProcessor: String? = null
    var visualizer: String? = null
    var batchSize: Int
    var inputSize: Int

    constructor(
        model: String,
        trainDataloader: String,
        testDataloader: String,
        optimizer: String,
        lossFunction: String,
        learningRate: Double,
        batchSize: Int,
        inputSize: Int
    ) {
        this.model = model
        this.trainDataloader = trainDataloader
        this.testDataloader = testDataloader
        this.optimizer = optimizer
        this.lossFunction = lossFunction
        this.learningRate = learningRate
        this.batchSize = batchSize
        this.inputSize = inputSize
    }

    constructor(
        model: String,
        trainDataloader: String,
        validDataloader: String,
        testDataloader: String,
        optimizer: String,
        lossFunction: String,
        learningRate: Double,
        batchSize: Int,
        inputSize: Int
    ) : this(
        model,
        trainDataloader,
        testDataloader,
        optimizer,
        lossFunction,
        learningRate,
        batchSize,
        inputSize
    ) {
        this.validDataloader = validDataloader
    }

    fun info() {
        println("Model: $model")
        println("Train DataLoader: $trainDataloader")
        println("Valid DataLoader: $validDataloader")
        println("Test DataLoader: $testDataloader")
        println("Optimizer: $optimizer")
        println("Loss Function: $lossFunction")
        println("Learning Rate: $learningRate")
        println("Pre Processor: $preProcessor")
        println("Post Processor: $postProcessor")
        println("Visualizer: $visualizer")
        println("Batch Size: $batchSize")
        println("Input Size: $inputSize")
    }
}