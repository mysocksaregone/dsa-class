import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.example.stackList
class StackTest {
    @Test
    fun pushAddsElementToTop() {
        val stack = stackList<Int>()

        stack.push(10)
        stack.push(20)

        assertEquals(20, stack.peek())
    }

    @Test
    fun popRemovesAndReturnsTopElement() {
        val stack = stackList<Int>()

        stack.push(10)
        stack.push(20)

        assertEquals(20, stack.pop())
        assertEquals(10, stack.peek())
    }
    @Test
    fun stackFollowsLastInFirstOut() {
        val stack = stackList<Int>()

        stack.push(10)
        stack.push(20)
        stack.push(30)

        assertEquals(30, stack.pop())
        assertEquals(20, stack.pop())
        assertEquals(10, stack.pop())
        assertTrue(stack.isEmpty())
    }

    @Test
    fun isEmptyReturnsTrueWhenEmpty() {
        val stack = stackList<Int>()

        assertTrue(stack.isEmpty())
    }

    @Test
    fun isEmptyReturnsFalseAfterPush() {
        val stack = stackList<Int>()

        stack.push(10)

        assertFalse(stack.isEmpty())
    }

    @Test
    fun reverseStackWorks() {
        val stack = stackList<Int>()

        stack.push(1)
        stack.push(2)
        stack.push(3)

        val reversed = stack.reverseStack()

        assertEquals(1, reversed.pop())
        assertEquals(2, reversed.pop())
        assertEquals(3, reversed.pop())
        assertEquals(null, reversed.pop())
    }
}