import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.example.queueList

class QueueTest {
    @Test
    fun enqueueAddsElementToBack() {
        val queue = queueList<Int>()

        queue.enqueue(10)
        queue.enqueue(20)

        assertEquals(10, queue.peek())
    }

    @Test
    fun dequeueRemovesAndReturnsFrontElement() {
        val queue = queueList<Int>()

        queue.enqueue(10)
        queue.enqueue(20)

        assertEquals(10, queue.dequeue())
        assertEquals(20, queue.peek())
    }
    
    @Test
    fun queueFollowsFirstInFirstOut() {
        val queue = queueList<Int>()

        queue.enqueue(10)
        queue.enqueue(20)
        queue.enqueue(30)

        assertEquals(10, queue.dequeue())
        assertEquals(20, queue.dequeue())
        assertEquals(30, queue.dequeue())
        assertTrue(queue.isEmpty())
    }

    @Test
    fun isEmptyReturnsTrueWhenEmpty() {
        val queue = queueList<Int>()

        assertTrue(queue.isEmpty())
    }

    @Test
    fun isEmptyReturnsFalseAfterEnqueue() {
        val queue = queueList<Int>()

        queue.enqueue(10)

        assertFalse(queue.isEmpty())
    }
}