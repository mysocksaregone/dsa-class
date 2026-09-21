// Exercise 1
package org.example

interface LinkedList<T> {
    /**
     * Adds the element [data] to the front of the linked list.
     */
    fun pushFront(data: T)

    /**
     * Adds the element [data] to the back of the linked list.
     */
    fun pushBack(data: T)

    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T?

    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T?

    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T?

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T?

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

class DoublyLinkedList<T> : LinkedList<T>
{
    class Node<T>(var data: T,
                  var next: Node<T>? = null,
                  var previous: Node<T>? = null)
    {
    }

    // reference to start of the list
    var head : Node<T>? = null

    // reference to end of the list
    var tail : Node<T>? = null

    override fun pushFront(data: T) {
        val nodeNew = Node(data)

        // if the list is empty
        if (head == null) {

            // both the head and the tail reference new node
            head = nodeNew
            tail = nodeNew
        } else {
            // in normal cases...

            // moving over previous head
            nodeNew.next = head

            // previous one before head is the new node
            head!!.previous = nodeNew

            // new node becomes head
            head = nodeNew
        }
    }

    override fun pushBack(data: T) {
        val nodeNew = Node(data)

        if (tail == null) {

            // both the head and the tail reference new node
            head = nodeNew
            tail = nodeNew
        } else {
            // in normal cases...

            // moving back previous tail
            nodeNew.previous = tail

            // ndoe after tail is now new node
            tail!!.next = nodeNew

            // new node becomes tail
            tail = nodeNew
        }

    }

    override fun popFront(): T? {
        // use elvis operator to return default val
        val currentHead = head ?: return null

        // in the scenario that the list is one val long
        if (head == tail)
        {
            head = null
            tail = null
        } else // all other cases
        {
            head = currentHead.next
            head!!.previous = null
        }

        // return the one that got deleted
        return currentHead.data

    }

    override fun popBack(): T? {
        val currentTail = tail ?: return null

        // same scenario of 1 length
        if (head == tail)
        {
            head = null
            tail = null
        } else // all other cases
        {
            tail = currentTail.previous
            tail!!.next = null
        }

        // return the one that got deleted
        return currentTail.data
    }

    override fun peekFront(): T? {
        return head?.data
    }

    override fun peekBack(): T? {
        return tail?.data
    }

    override fun isEmpty(): Boolean {

        // provide the boolean in the return statement
        // instead of making a redundant if then statement
        return head == null
    }

}