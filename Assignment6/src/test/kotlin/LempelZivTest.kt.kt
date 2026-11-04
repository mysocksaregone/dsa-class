package org.example

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class LempelZivTest {
    @Test
    fun emptyStringProducesNoBytes() {
        assertEquals(0, LempelZiv("").size)
    }

    @Test
    fun singleCharacterIsOnePair() {
        assertEquals(3, LempelZiv("a").size)
    }

    @Test
    fun singleCharacterIsPrefixZeroPlusChar() {
        // (0,'a') = 000000000000 01100001 + 4 padding bits
        assertContentEquals(byteArrayOf(0x00, 0x06, 0x10), LempelZiv("a"))
    }

    @Test
    fun repeatedPattern() {
        assertEquals(13, LempelZiv("abababab").size)
    }

    @Test
    fun repetitiveInputCompresses() {
        val input = "a".repeat(1000)
        assertTrue(LempelZiv(input).size < input.length)
    }

    @Test
    fun inputPastCodebookLimitDoesNotCrash() {
        // enough distinct phrases to exceed 4095 entries
        val input = (0 until 40_000).map { 'a' + (it * it % 11) }.joinToString("")
        LempelZiv(input)
    }

    // no ascii for non latin letters lolol
    @Test
    fun charactersAbove255Throw() {
        assertFailsWith<IllegalArgumentException> { LempelZiv("€") }
    }
}