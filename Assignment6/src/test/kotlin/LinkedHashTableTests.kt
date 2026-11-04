package org.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LinkedHashTableTest {

    private fun newTable() = LinkedHashTable<Int, String>()

    @Test
    fun emptyTable() {
        val t = newTable()
        assertEquals(0, t.size())
        assertNull(t[5])
        // contains 5?
        assertFalse(5 in t)
        assertTrue(t.keyValuePairs().isEmpty())
    }

    @Test
    fun postSetting() {
        val t = newTable()
        t[1] = "one"
        assertEquals("one", t[1])
        assertTrue(1 in t)
    }

    @Test
    fun nullCheck() {
        val t = newTable()
        t[1] = "hi"
        assertNull(t[2])
    }

    @Test
    fun checkGrowth() {
        val t = newTable()
        t[1] = "a"
        t[2] = "b"
        t[3] = "c"
        assertEquals(3, t.size())

    }

    @Test
    fun sizeUnchangingAfterOverwrite() {
        val t = newTable()
        t[1] = "old"
        t[1] = "new"
        assertEquals(1, t.size())
    }

    @Test
    fun sizeChangingafterRemoval() {
        val t = newTable()
        t[1] = "one"
        t[2] = "two"
        assertTrue(t.remove(1))
        assertFalse(1 in t)
        assertNull(t[1])
        assertEquals(1, t.size())
    }

    // initial table size of 53
    @Test
    fun collidingKeysAreStoredSeparately() {
        val t = newTable()
        t[0] = "a"
        t[53] = "b"
        t[106] = "c"
        assertEquals("a", t[0])
        assertEquals("b", t[53])
        assertEquals("c", t[106])
        assertEquals(3, t.size())
    }

    @Test
    fun removingOneCollidingKeyLeavesTheOthers() {
        val t = newTable()
        t[0] = "a"
        t[53] = "b"
        t[106] = "c"
        t.remove(53)
        assertEquals("a", t[0])
        assertNull(t[53])
        assertEquals("c", t[106])
        assertEquals(2, t.size())
    }

    @Test
    fun keyValuePairsContainsEverything() {
        val t = newTable()
        t[1] = "a"
        t[2] = "b"
        t[53] = "c"
        assertEquals(
            setOf(1 to "a", 2 to "b", 53 to "c"),
            t.keyValuePairs().toSet()
        )
        assertEquals(3, t.keyValuePairs().size)
    }

    @Test
    fun keyValuePairsConsistentOverwriteAndRemove() {
        val t = newTable()
        t[1] = "a"
        t[2] = "b"
        t[1] = "A"
        t.remove(2)
        assertEquals(listOf(1 to "A"), t.keyValuePairs())
    }

    @Test
    fun removeWorksAfterRehash() {
        val t = newTable()
        for (i in 0 until 500) t[i] = "v$i"
        for (i in 0 until 500 step 2) assertTrue(t.remove(i))
        assertEquals(250, t.size())
        for (i in 0 until 500) {
            if (i % 2 == 0) assertNull(t[i]) else assertEquals("v$i", t[i])
        }
    }

    @Test
    fun overwriteAndKeyValuePairsWorkAfterRehash() {
        val t = newTable()
        for (i in 0 until 300) t[i] = "old"
        for (i in 0 until 300) t[i] = "new"
        assertEquals(300, t.size())
        assertEquals(300, t.keyValuePairs().size)
        assertEquals((0 until 300).toSet(), t.keyValuePairs().map { it.first }.toSet())
        for (i in 0 until 300) assertEquals("new", t[i])
    }

    @Test
    fun negativeKeysWork() {
        val t = newTable()
        t[-7] = "neg"
        assertEquals("neg", t[-7])
        assertTrue(-7 in t)
    }

    @Test
    fun largeKeysHashToValidBuckets() {
        val t = newTable()
        t[Int.MAX_VALUE] = "max"
        t[Int.MIN_VALUE] = "min"
        assertEquals("max", t[Int.MAX_VALUE])
        assertEquals("min", t[Int.MIN_VALUE])
    }
}
