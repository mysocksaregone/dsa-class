package org.example

/**
 * ``Graph`` represents a directed graph
 * @param VertexType the type that represents a vertex in the graph
 */
interface Graph<VertexType> {
    /**
     * @return the vertices in the graph
     */
    fun getVertices(): Set<VertexType>

    /**
     * Add an edge between [from] and [to] with edge weight [cost]
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double)

    /**
     * Get all the edges that begin at [from]
     * @return a map where each key represents a vertex connected to [from] and the value represents the edge weight.
     */
    fun getEdges(from: VertexType): Map<VertexType, Double>

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear()
}

class DirectedWeightedGraph<VertexType> : Graph<VertexType> {
    /**
     * A directed graph where each edge has a numerical cost.
     *
     * @param //VertexType the type used to represent vertices in the graph.
     */

    private var adjacency: MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()

    // shorthand function return, return all keys since it contains all vertices

    /**
     * Returns all vertices currently contained in the graph.
     *
     * @return a set containing every vertex in the graph.
     */
    override fun getVertices(): Set<VertexType> = adjacency.keys.toSet()

    /**
     * Adds a directed edge from one vertex to another with the given cost.
     *
     * If the edge already exists, its cost is replaced with the new cost.
     *
     * @param from the vertex the edge starts at.
     * @param to the vertex the edge points to.
     * @param cost the cost associated with the edge.
     */
    override fun addEdge(from: VertexType, to:VertexType, cost: Double) {

        // getOrPut() function gives value for a key but ignores if null
        adjacency.getOrPut(key = from) { mutableMapOf() } [to] = cost
        adjacency.getOrPut(key = to) { mutableMapOf() } // ensures that to is a vertex
    }

    /**
     * Returns all outgoing edges from a provided vertex.
     *
     * The returned map contains each neighboring vertex and the cost
     * of the edge connecting it to [from].
     *
     * @param from the vertex whose outgoing edges should be returned.
     * @return a map of neighboring vertices to their edge costs, or an
     * empty map if the vertex does not exist.
     */
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        // allows it to be null, returns empty map otherwise
        return adjacency[from]?.toMap() ?: emptyMap()
    }

    /**
     * Removes all vertices and edges from the graph.
     */
    override fun clear()
    {
        adjacency.clear()
    }
}

