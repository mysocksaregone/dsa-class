package org.example

interface Stack<T> {
    /**
     * Add [data] to the top of the stack
     */
    fun push(data: T)
    /**
     * Remove the element at the top of the stack.  If the stack is empty, it remains unchanged.
     * @return the value at the top of the stack or nil if none exists
     */
    fun pop(): T?
    /**
     * @return the value on the top of the stack or nil if none exists
     */
    fun peek(): T?
    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

class stackList<T> : Stack<T> {
    private val list = DoublyLinkedList<T>()

    override fun push(data: T)
    {
        list.pushFront(data)
    }

    override fun pop(): T? {
        return list.popFront()
    }

    override fun peek(): T? {
        return list.peekFront()
    }

    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    fun reverseStack(): stackList<T> {
        // reversed stack to return
        val reversedStack = stackList<T>()

        // repeat until the list is empty
        while (!this.isEmpty()){
            // pop every value and store it
            val value = this.pop()

            // put this value at the top of reversed stack
            if (value != null) {
                reversedStack.push(value)
            }
        }

        // reversed stack!
        return reversedStack
    }
}
