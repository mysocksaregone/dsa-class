package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class DijkstraTest {
    @Test
    fun `Dijkstra should find shortest path`() {
        val graph = DirectedWeightedGraph<String>()

        graph.addEdge("Boston", "New York", 4.0)
        graph.addEdge("Boston", "Chicago", 8.0)
        graph.addEdge("New York", "Chicago", 2.0)
        graph.addEdge("Chicago", "Denver", 5.0)
        graph.addEdge("Denver", "Seattle", 6.0)

        val path = Dijkstra(
            graph,
            "Boston",
            "Seattle"
        )

        assertEquals(
            listOf(
                "Boston",
                "New York",
                "Chicago",
                "Denver",
                "Seattle"
            ),
            path
        )
    }

    @Test
    fun `Dijkstra should return direct path when it is shortest`() {
        val graph = DirectedWeightedGraph<String>()

        graph.addEdge("Boston", "Chicago", 5.0)
        graph.addEdge("Boston", "New York", 10.0)
        graph.addEdge("New York", "Chicago", 10.0)

        val path = Dijkstra(
            graph,
            "Boston",
            "Chicago"
        )

        assertEquals(
            listOf("Boston", "Chicago"),
            path
        )
    }

    @Test
    fun `Dijkstra should return null when no path exists`() {
        val graph = DirectedWeightedGraph<String>()

        graph.addEdge("Boston", "New York", 4.0)
        graph.addEdge("Chicago", "Denver", 5.0)

        val path = Dijkstra(
            graph,
            "Boston",
            "Denver"
        )

        assertNull(path)
    }
}