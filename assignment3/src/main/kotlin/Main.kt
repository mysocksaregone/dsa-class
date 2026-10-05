package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun createCityGraph(): Graph<String> {
    val graph = DirectedWeightedGraph<String>()

    graph.addEdge("Boston", "New York", 4.0)
    graph.addEdge("Boston", "Chicago", 8.0)
    graph.addEdge("New York", "Philadelphia", 3.0)
    graph.addEdge("New York", "Chicago", 2.0)
    graph.addEdge("Chicago", "Denver", 2.0)
    graph.addEdge("Philadelphia", "Denver", 7.0)
    graph.addEdge("Denver", "Seattle", 6.0)
    graph.addEdge("Chicago", "Seattle", 12.0)

    return graph
}

fun main() {
    val graph = createCityGraph()

    val path = Dijkstra(graph, "Boston", "Seattle")

    println("Shortest path:")
    println(path)
}