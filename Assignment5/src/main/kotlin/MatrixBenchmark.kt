package org.example

import kotlin.random.Random
import kotlin.system.measureNanoTime

fun randomMatrix(size: Int): Matrix {
    val result = Matrix(size)

    for (i in 0 until size){
        for (j in 0 until size) {
            result.setElement(i, j, Random.nextDouble())
        }
    }

    return result
}

fun main() {
    benchMatrixMultiplication()
}

fun benchMatrixMultiplication() {
    val sizes = listOf(16, 32, 64, 128, 256)
    val cutoffs = listOf(16, 32, 64)

    println("Size | Regular ms | Strassen ms | Hybrid cutoff times (ms ")

    for (n in sizes) {
        val random = Random(42 + n)
        val a = randomMatrix(n, random)
        val b = randomMatrix(n, random)

        a.multiply(b)
        a.strassenMultiply(b)

        val regularTime = measureNanoTime {
            a.multiply(b)
        } / 1000000.0

        val strassenTime = measureNanoTime {
            a.strassenMultiply(b)
        } / 1000000.0

        // include several values accounting for different cutoff values
        val hybridTimes = cutoffs.joinToString { cutoff ->
            val time = measureNanoTime {
                a.hybridMultiply(b, cutoff)
            } / 1000000.0

            "$cutoff: ${"%.2f".format(time)}"
        }

        println(
            "$n | ${"%.2f".format(regularTime)} | " +
            "${"%.2f".format(strassenTime)} | $hybridTimes"
        )


    }
}