package org.example
import kotlin.random.Random
import kotlin.time.measureTime
import kotlin.time.DurationUnit

fun main() {
    // testing different list sizes
    val sizes = listOf(10, 100, 1000, 10000, 100000)

    // storing the results for each sorting algorithm
    val bubbleRunTimes = mutableListOf<Double>()
    val selectionRunTimes = mutableListOf<Double>()
    val mergeRunTimes = mutableListOf<Double>()
    val radixRunTimes = mutableListOf<Double>()

    // testing each list size
    for (desiredSize in sizes) {

        val x = (1 until desiredSize).map { Random.nextInt(100000) }

        // Bubble sort runtime
        val bubbleArray = x.toIntArray()
        val bubbleRunTime = measureTime {
            bubbleSort(bubbleArray)
        }
        bubbleRunTimes.add(
            bubbleRunTime.toDouble(DurationUnit.MILLISECONDS)
        )

        // Selection sort runtime
        val selectionArray = x.toIntArray()
        val selectionRunTime = measureTime {
            selectionSort(selectionArray)
        }
        selectionRunTimes.add(
            selectionRunTime.toDouble(DurationUnit.MILLISECONDS)
        )

        // Insertion sort runtime
        val mergeArray = x.toIntArray()
        val mergeRunTime = measureTime {
            mergeSort(mergeArray)
        }
        mergeRunTimes.add(
            mergeRunTime.toDouble(DurationUnit.MILLISECONDS)
        )

        // Radix sort runtime
        val radixArray = x.toIntArray()
        val radixRunTime = measureTime {
            radixSort(radixArray)
        }
        radixRunTimes.add(
            radixRunTime.toDouble(DurationUnit.MILLISECONDS)
        )
    }

    println("List sizes: $sizes")
    println("Bubble sort runtimes: $bubbleRunTimes")
    println("Selection sort runtimes: $selectionRunTimes")
    println("Merge sort runtimes: $mergeRunTimes")
    println("Radix sort runtimes: $radixRunTimes")
}