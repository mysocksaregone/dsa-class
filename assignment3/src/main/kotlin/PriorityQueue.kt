package org.example

/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 */
interface MinPriorityQueue<T> {
    /**
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean

    /**
     * Add [elem] with at level [priority]
     */
    fun addWithPriority(elem: T, priority: Double)

    /**
     * Get the next (highest priority) element and remove this element from the queue.
     * @return the next element in terms of priority.  If empty, return null.
     */
    fun next(): T?

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double)
}

class PriorityQueue<T> : MinPriorityQueue<T> {
    /**
     * A minimum priority queue implemented using a [MinHeap].
     *
     * @param T the type of element stored in the priority queue.
     */

    private val heap = MinHeap<T>()

    /**
     * Determines whether the priority queue is empty.
     *
     * @return true if the queue is empty, otherwise false.
     */
    override fun isEmpty(): Boolean {
        return heap.isEmpty()
    }

    /**
     * Adds an element to the queue with a given priority.
     *
     * @param elem the element to add.
     * @param priority the priority assigned to the element.
     */
    override fun addWithPriority(elem: T, priority: Double) {
        heap.insert( data = elem, heapNumber = priority)
    }

    /**
     * Removes and returns the element with the smallest priority.
     *
     * @return the minimum-priority element, or null if the queue is empty.
     */
    override fun next(): T? {
        return heap.getMin()
    }

    /**
     * Changes the priority of an existing element.
     *
     * @param elem the element whose priority should be changed.
     * @param newPriority the new priority for the element.
     */
    override fun adjustPriority(elem: T, newPriority: Double) {
        heap.adjustHeapNumber(elem, newPriority)
    }
}