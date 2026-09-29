package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class PriorityQueueTest {
    @Test
    fun `addWithPriority should add an element`() {
        val queue = PriorityQueue<String>()

        queue.addWithPriority("Boston", 5.0)

        assertEquals("Boston", queue.next())
    }

    @Test
    fun `next should return lowest priority element`() {
        val queue = PriorityQueue<String>()

        queue.addWithPriority("Boston", 5.0)
        queue.addWithPriority("Chicago", 2.0)
        queue.addWithPriority("Denver", 8.0)

        assertEquals("Chicago", queue.next())
    }

    @Test
    fun `next on empty queue should return null`() {
        val queue = PriorityQueue<String>()

        assertNull(queue.next())
    }

    @Test
    fun `adjustPriority should update element priority`() {
        val queue = PriorityQueue<String>()

        queue.addWithPriority("Boston", 10.0)
        queue.addWithPriority("Chicago", 5.0)

        queue.adjustPriority("Boston", 1.0)

        assertEquals("Boston", queue.next())
    }
}