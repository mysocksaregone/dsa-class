package org.example
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

// brute force nearest neighbor, checking every single point
fun bruteForce(points: List<List<Double>>, query: List<Double>) : List<Double>{
    var best = points[0]
    var bestDist = squaredDistance(best, query)
    for (p in points) {
        val d = squaredDistance(p, query)
        if (d < bestDist) {
            best = p
            bestDist = d
        }
    }

    return best
}

// benchmarking
fun randomPoints(n: Int, k: Int): List<List<Double>> {
    return List(n) { List(k) {Random.nextDouble()} }
}

/**
 * Benchmark KD trees against brute force nearest neighbor.
 * 1000 test points will be generated to test against the training
 * points.
 *
 * @param k: the dimensionality of the dataset to create
 * @param numPoints = 1000: the number of points to use to match against
 * @return the triple of duration objects where the first specifies
 * the time to build the tree, the second specifies the time it takes
 * to query the tree with 1,000 points, and the third is the time it
 * takes to find the nearest neighbor to these points using the brute
 * force approach.
 */
fun runExperiment(k: Int, numPoints: Int): Triple<Duration, Duration, Duration> {
    val training = randomPoints(numPoints, k)
    val tests = randomPoints(1000, k)

    // time to build tree
    val (tree, buildTime) = measureTimedValue { KDTree(training)}

    // time for 1000 queries on tree
    val treeAnswers = mutableListOf<List<Double>>()
    val treeTime = measureTime {
        for (q in tests) {
            treeAnswers.add(tree.nearest(q))
        }
    }

    // time for 1000 queries brute force
    val bruteAnswers = mutableListOf<List<Double>>()
    val bruteTime = measureTime {
        for (q in tests) {
            bruteAnswers.add(bruteForce(training, q))
        }
    }

    // both methods should find equally close points
    for (i in tests.indices) {
        check(squaredDistance(treeAnswers[i], tests[i]) == squaredDistance(bruteAnswers[i], tests[i])) {
            "Tree and brute force do not match distances."
        }
    }

    return Triple(buildTime, treeTime, bruteTime)
}

fun main() {
    runExperiment(3, 1000)

    println("| k | numPoints | build | tree queries | brute force queries |")
    println("|---|-----------|-------|--------------|---------------------|")
    for (k in listOf(1, 2, 3, 5, 10, 20)) {
        for (n in listOf(10, 100, 1000, 10000, 100000)) {
            val (build, tree, brute) = runExperiment(k, n)
            println("| $k | $n | $build | $tree | $brute |")
        }
    }
}