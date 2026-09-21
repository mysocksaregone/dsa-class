import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.example.DoublyLinkedList

class DoublyLinkedListTest {
    @Test
    fun pushFrontAddsElementToFront() {
        val list = DoublyLinkedList<Int>()

        list.pushFront(10)
        list.pushFront(20)
        list.pushFront(30)

        assertEquals(30, list.peekFront())
        assertEquals(10, list.peekBack())
    }

    @Test
    fun pushBackAddsElementToBack() {
        val list = DoublyLinkedList<Int>()

        list.pushBack(10)
        list.pushBack(20)
        list.pushBack(30)

        assertEquals(10, list.peekFront())
        assertEquals(30, list.peekBack())
    }

    @Test
    fun popFrontRemovesAndReturnsFrontElement() {
        val list = DoublyLinkedList<Int>()

        list.pushBack(10)
        list.pushBack(20)
        list.pushBack(30)

        assertEquals(10, list.popFront())
        assertEquals(20, list.peekFront())
    }

    @Test
    fun popBackRemovesAndReturnsBackElement() {
        val list = DoublyLinkedList<Int>()

        list.pushBack(10)
        list.pushBack(20)
        list.pushBack(30)

        assertEquals(30, list.popBack())
        assertEquals(20, list.peekBack())
    }

    @Test
    fun isEmptyReturnsTrueForEmptyList() {
        val list = DoublyLinkedList<Int>()

        assertTrue(list.isEmpty())
    }

    @Test
    fun isEmptyReturnsFalseForNonEmptyList() {
        val list = DoublyLinkedList<Int>()

        list.pushFront(10)

        assertFalse(list.isEmpty())
    }

    @Test
    fun poppingEmptyListReturnsNull() {
        val list = DoublyLinkedList<Int>()

        assertNull(list.popFront())
        assertNull(list.popBack())
    }

}