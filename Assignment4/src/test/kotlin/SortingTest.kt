package org.example

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class SortingTest {

    @Test
    fun `bubble sort sorts array correctly`() {
        val array = intArrayOf(5, 2, 8, 1, 3)

        bubbleSort(array)

        assertArrayEquals(
            intArrayOf(1, 2, 3, 5, 8),
            array
        )
    }

    @Test
    fun `bubble sort handles duplicates`() {
        val array = intArrayOf(4, 2, 4, 1, 2)

        bubbleSort(array)

        assertArrayEquals(
            intArrayOf(1, 2, 2, 4, 4),
            array
        )
    }

    @Test
    fun `selection sort sorts array correctly`() {
        val array = intArrayOf(5, 2, 8, 1, 3)

        selectionSort(array)

        assertArrayEquals(
            intArrayOf(1, 2, 3, 5, 8),
            array
        )
    }
    @Test
    fun `selection sort handles already sorted array`() {
        val array = intArrayOf(1, 2, 3, 4, 5)

        selectionSort(array)

        assertArrayEquals(
            intArrayOf(1, 2, 3, 4, 5),
            array
        )
    }

    @Test
    fun `merge sort sorts array correctly`() {
        val array = intArrayOf(5, 2, 8, 1, 3)

        val result = mergeSort(array)

        assertArrayEquals(
            intArrayOf(1, 2, 3, 5, 8),
            result
        )
    }

    @Test
    fun `merge sort handles empty array`() {
        val array = intArrayOf()

        val result = mergeSort(array)

        assertArrayEquals(intArrayOf(), result)
    }

    @Test
    fun `merge sort handles reverse sorted array`() {
        val array = intArrayOf(5, 4, 3, 2, 1)

        val result = mergeSort(array)

        assertArrayEquals(
            intArrayOf(1, 2, 3, 4, 5),
            result
        )
    }

    @Test
    fun `radix sort sorts array correctly`() {
        val array = intArrayOf(5, 2, 8, 1, 3)

        val result = radixSort(array)

        assertArrayEquals(
            intArrayOf(1, 2, 3, 5, 8),
            result
        )
    }

    @Test
    fun `radix sort handles single element`() {
        val array = intArrayOf(42)

        val result = radixSort(array)

        assertArrayEquals(intArrayOf(42), result)
    }

    @Test
    fun `radix sort handles negative numbers`() {
        val array = intArrayOf(-5, 3, -10, 7, 0, -2)

        val result = radixSort(array)

        assertArrayEquals(
            intArrayOf(-10, -5, -2, 0, 3, 7),
            result
        )
    }
}