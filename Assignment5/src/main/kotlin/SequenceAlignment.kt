package org.example

data class AlignmentResult(
    val score: Int,
    val alignedSequence1: String,
    val alignedSequence2: String
)

fun needlemanWunsch(
    sequence1: String,
    sequence2: String,
    matchScore: Int = 1,
    mismatchPenalty: Int = -1,
    gapPenalty: Int = -1
): AlignmentResult {
    val rows = sequence1.length + 1
    val cols = sequence2.length + 1

    // making a score table
    val scores = Array(rows) { IntArray(cols)}

    // making a direction table
    val directions = Array(rows) { IntArray(cols)}

    // first column is initialized with gap penalty
    for (r in 1 until rows) {
        scores[r][0] = r * gapPenalty
        directions[r][0] = 1
    }

    // first row is also initialized with gap penalty
    for (c in 1 until cols) {
        scores[0][c] = c * gapPenalty
        directions[0][c] = 2
    }

    // fill in score table from index 1 (ignore first row for dp) 
    for (r in 1 until rows){
        for (c in 1 until cols) {
            var term = 0
            if (sequence1[r-1] == sequence2[c-1]) {
                term = matchScore
            } else {
                term = mismatchPenalty
            }
            
            // defining scores for each direction 
            val diagonalScore = scores[r-1][c-1] + term
            
            val upScore = scores[r-1][c] + gapPenalty
            val leftScore = scores[r][c-1] + gapPenalty
            
            // picking the largest of the scores 
            val bestScore = maxOf(diagonalScore, upScore, leftScore)

            // save the best score in the score table
            scores[r][c] = bestScore

            // correspond directions to numbers
            // when scores are tied, prefer diagonal then up
            directions[r][c] = when {
                bestScore == diagonalScore -> 0
                bestScore == upScore -> 1
                else -> 2
            }
        }
    }

    // reconstruct aligned sequences

    var r = sequence1.length
    var c = sequence2.length

    // chose stringbuilder for multiple string manipulation operations
    val aligned1 = StringBuilder()
    val aligned2 = StringBuilder()

    // while we haven't reached the beginning...
    while(r > 0 || c > 0) {
        when {
            // direction is diagonal and no gap
            r > 0 && c > 0 && directions[r][c] == 0 -> {

                // starting at bottom right
                aligned1.append(sequence1[r - 1])
                aligned2.append(sequence2[c - 1])
                r--
                c--
            }

            // direction is up and gap
            r > 0 && (c == 0 || directions[r][c] == 1) -> {
                aligned1.append(sequence1[r - 1])
                aligned2.append('-')
                r--
            }

            // direction is left and gap
            else -> {
                aligned1.append('-')
                aligned2.append(sequence2[c - 1])
                c--
            }
        }
    }

    return AlignmentResult(
        score = scores[sequence1.length][sequence2.length],
        alignedSequence1 = aligned1.reverse().toString(),
        alignedSequence2 = aligned2.reverse().toString()
    )
}