package org.example

fun testIdenticalSequences() {
    val result = needlemanWunsch("ACGT", "ACGT")

    check(result.score == 4)
    check(result.alignedSequence1 == "ACGT")
    check(result.alignedSequence2 == "ACGT")
}

fun testKnownAlignment() {
    val result = needlemanWunsch("ACG", "AG")

    check(result.score == 1)
    check(result.alignedSequence1 == "ACG")
    check(result.alignedSequence2 == "A-G")
}

fun testMismatch() {
    val result = needlemanWunsch("A", "G")

    check(result.score == -1)
    check(result.alignedSequence1 == "A")
    check(result.alignedSequence2 == "G")
}

fun testEmptySequence() {
    val result = needlemanWunsch("", "AC")

    check(result.score == -2)
    check(result.alignedSequence1 == "--")
    check(result.alignedSequence2 == "AC")
}

fun testBothSequencesEmpty() {
    val result = needlemanWunsch("", "")

    check(result.score == 0)
    check(result.alignedSequence1.isEmpty())
    check(result.alignedSequence2.isEmpty())
}

fun testAlignmentLengthsMatch() {
    val result = needlemanWunsch("GATTACA", "GCATGCU")

    check(
        result.alignedSequence1.length ==
                result.alignedSequence2.length
    )
}

fun main() {
    testIdenticalSequences()
    testKnownAlignment()
    testMismatch()
    testEmptySequence()
    testBothSequencesEmpty()
    testAlignmentLengthsMatch()

    println("All alignment tests passed!")
}