package org.example



// bubble sort!
// simple sort, compare a pair of elements next to each other and
// swap the two if the left is greater than the right
fun bubbleSort(array: IntArray) {
    val size = array.size

    // for every value in the list..
    for (i in 0..<size - 1) {

        // go through entire list swapping
        for (j in 0..<(size - i -1)) {

            // if the current element is greater than the next...
            if (array[j] > array [j+1]) {

                // store the original value
                val storage = array[j]

                // swap
                array[j] = array[j+1]
                array[j+1] = storage
            }
        }
    }
}

// selection sort!
// goes through the entire array to find the
// lowest value and moves it to the front
fun selectionSort(array: IntArray) {
    val size = array.size

    for (i in 0 until size-1 ) {

        // assume the current index holds the smallest val in array
        var minIndex = i

        // search the rest of the array for smaller values
        for (j in (i + 1) until size) {
            if (array[j] < array[minIndex]) {
                minIndex = j
            }
        }

        // swap the smallest value into the smallest index
        val temp = array[i]
        array[i] = array[minIndex]
        array[minIndex] = temp
    }
}

// function used to merge separated arrays back together
// created for and used within merge sort
fun merge(leftArray: IntArray, rightArray: IntArray): IntArray {

    // instantiate merged array to return size of both left and right size array
    val mergedArray = IntArray(leftArray.size + rightArray.size)

    // starting index of the left array
    var leftIndex = 0

    // starting index of the right array
    var rightIndex = 0

    // starting index of the merged array
    var mergedIndex = 0

    while (leftIndex < leftArray.size && rightIndex < rightArray.size) {

        // if the current left value is less than (or equal to) current right value
        if (leftArray[leftIndex] <= rightArray[rightIndex]) {
            mergedArray[mergedIndex] = leftArray[leftIndex]
            leftIndex++
        } else {
            mergedArray[mergedIndex] = rightArray[rightIndex]
            rightIndex++
        }

        // update merged index
        mergedIndex++
    }

    // if the while loop above finishes and left array is still not complete
    // add the rest of the array into the merged array
    while (leftIndex < leftArray.size) {
        mergedArray[mergedIndex] = leftArray[leftIndex]
        leftIndex++
        mergedIndex++
    }

    // same logic as above, but relay onto right array
    while (rightIndex < rightArray.size) {
        mergedArray[mergedIndex] = rightArray[rightIndex]
        rightIndex++
        mergedIndex++
    }

    return mergedArray
}

// merge sort!
fun mergeSort(array: IntArray) : IntArray {
    // storing start, end, and middle index
    if (array.size <= 1) {
        return array
    }

    val startIndex = 0
    val endIndex = array.size - 1
    var middleIndex = (array.size - 1) / 2

    // defining the array on the left and the array on the right
    val leftArray = array.sliceArray(startIndex..middleIndex)
    val rightArray = array.sliceArray(middleIndex + 1..endIndex)

    // merge the left and right sides after sorting
    return merge(mergeSort(leftArray), mergeSort(rightArray))
}

// radix sort!
// separates the values by their lowest digit (one's place)
// sorts them using bubble sort and then checks the
// next greater digit and sorts them again until it's all sorted
fun radixSort(array: IntArray): IntArray {

    if (array.size <= 1) return array

    val minValue = array.min()

    // done specifically to ensure that negative values work as well
    val maxValueShifted = array.max().toLong() - minValue

    var result = array.copyOf()

    // making it into a long value just in case it becomes
    // too big
    var exp = 1L

    while (maxValueShifted / exp > 0) {
        val buckets = List(10) {mutableListOf<Int>()}

        // going through each digit of the largest value
        for (num in result) {

            // finding each digit (being xtra careful with types for size)...
            val digit = (((num.toLong() - minValue) / exp) % 10).toInt()

            // sorting them into buckets based on digit
            buckets[digit].add(num)
        }

        var index = 0

        // for every group of digits, sort them and change them
        // back into intArrays so they work with bubble sort
        for (bucket in buckets) {
            val bucketArray = bucket.toIntArray()
            bubbleSort(bucketArray)

            for (num in bucketArray) {
                result[index] = num
                index++
            }
        }

        exp *= 10
    }
    return result
}

