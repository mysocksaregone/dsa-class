package org.example

/**
 * Finds the shortest path between two vertices using Dijkstra's algorithm.
 *
 * All edge costs must be non-negative for Dijkstra's algorithm to produce
 * a correct result.
 *
 * @param graph the graph to search.
 * @param start the vertex where the path begins.
 * @param target the vertex where the path should end.
 * @return a list containing the vertices in the shortest path from [start]
 * to [target], or null if no path exists.
 */
fun <VertexType> Dijkstra ( graph: Graph<VertexType>, start: VertexType, target: VertexType): List<VertexType>? {
    // shortest known distance from start to each vertex
    val distance = mutableMapOf<VertexType, Double>()

    // stores previous vertex
    val previous = mutableMapOf<VertexType, VertexType>()

    // contains all vertices with priority
    val queue = PriorityQueue<VertexType>()

    // set all distances to infinity to start off
    for (vertex in graph.getVertices()) {
        distance[vertex] = Double.POSITIVE_INFINITY
    }

    // the initial distance
    distance[start] = 0.0

    // every vertex getting placed in priority queue
    for (vertex in graph.getVertices()) {
        if (vertex == start) {
            queue.addWithPriority( elem = vertex, priority = 0.0)
        } else {
            queue.addWithPriority(elem = vertex, priority = Double.POSITIVE_INFINITY)
        }
    }

    // while queue is not empty
    while (!queue.isEmpty()) {
        // vertex with the smallest distance, break if null
        val current = queue.next() ?: break

        // found goal
        if (current == target) {
            break
        }

        // check every edge of the smallest vertex
        for ((neighbor, cost) in graph.getEdges(from = current)) {

            // distance to neighbor, CANNOT be null
            val newDist = distance[current]!! + cost

            // if shorter path present, update it
            if (newDist < distance[neighbor]!!) {

                // shortest distance update
                distance[neighbor] = newDist

                // vertex before
                previous[neighbor] = current

                // update priority queue
                queue.adjustPriority(elem = neighbor, newPriority = newDist)
            }
        }
    }

    // in the case where there's no path
    if (distance[target] == Double.POSITIVE_INFINITY) {
        return null
    }

    // provided return
    return generateSequence(seed = target) { current -> previous[current] }.toList().asReversed()

}