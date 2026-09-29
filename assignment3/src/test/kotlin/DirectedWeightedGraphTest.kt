package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DirectedWeightedGraphTest {
    @Test
    fun `new graph should have no vertices`() {
        val graph = DirectedWeightedGraph<String>()

        assertTrue(graph.getVertices().isEmpty())
    }

    @Test
    fun `adding an edge should add both vertices`() {
        val graph = DirectedWeightedGraph<String>()

        graph.addEdge("Boston", "New York", 4.0)

        assertEquals(
            setOf("Boston", "New York"),
            graph.getVertices()
        )
    }

    @Test
    fun `getEdges should return outgoing edges`() {
        val graph = DirectedWeightedGraph<String>()

        graph.addEdge("Boston", "New York", 4.0)
        graph.addEdge("Boston", "Chicago", 8.0)

        assertEquals(
            mapOf(
                "New York" to 4.0,
                "Chicago" to 8.0
            ),
            graph.getEdges("Boston")
        )
    }

    @Test
    fun `getEdges should return empty map for unknown vertex`() {
        val graph = DirectedWeightedGraph<String>()

        assertTrue(graph.getEdges("Boston").isEmpty())
    }

}