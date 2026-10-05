package edu.datastructures

// val if it's immutable
// var if it's mutable
class IntStack {


    class IntStackNode(val data: Int, val next: IntStackNode?)
    {
    }
    // ? indicates nullable types
    var top: IntStackNode? = null
    // inherit the ? to avoid errors
    fun peek(): Int? {
        // allows top to be null
        return top?.data
    }

    fun isEmpty(): Boolean {
        return top == null
    }

    fun push(newData: Int){
        val x = IntStackNode(newData, next = top)
        top = x
    }

    fun pop(): Int? {
        val topValue = peek()
        top = top?.next
        return topValue
    }

}