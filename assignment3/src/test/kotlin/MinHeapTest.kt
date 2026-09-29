package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MinHeapTest {
    @Test
    fun `insert should add element`() {
        val heap = MinHeap<String>()

        assertTrue(heap.insert("Boston", 5.0))
        assertTrue(heap.contains("Boston"))
        assertFalse(heap.isEmpty())
    }

    @Test
    fun `getMin should return lowest priority element`() {
        val heap = MinHeap<String>()

        heap.insert("Boston", 5.0)
        heap.insert("Chicago", 2.0)
        heap.insert("Denver", 8.0)

        assertEquals("Chicago", heap.getMin())
    }

    @Test
    fun `getMin should return elements in priority order`() {
        val heap = MinHeap<String>()

        heap.insert("Boston", 5.0)
        heap.insert("Chicago", 2.0)
        heap.insert("Denver", 8.0)
        heap.insert("Seattle", 1.0)

        assertEquals("Seattle", heap.getMin())
        assertEquals("Chicago", heap.getMin())
        assertEquals("Boston", heap.getMin())
        assertEquals("Denver", heap.getMin())
    }

    @Test
    fun `getMin on empty heap should return null`() {
        val heap = MinHeap<Int>()

        assertNull(heap.getMin())
    }

    @Test
    fun `adjustHeapNumber should change priority`() {
        val heap = MinHeap<String>()

        heap.insert("Boston", 10.0)
        heap.insert("Chicago", 5.0)

        heap.adjustHeapNumber("Boston", 1.0)

        assertEquals("Boston", heap.getMin())
    }

}