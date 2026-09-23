package org.example


class Graph<VertexType> {
    private var adjacencyList: MutableMap<VertexType, MutableSet<VertexType>> = mutableMapOf()

    fun addVertex(v: VertexType): Boolean {
        if (adjacencyList.contains(v)) {
            return false
        }
        adjacencyList[v] = mutableSetOf()
        return true
    }

    fun addEdge(from: VertexType, to: VertexType): Boolean {
        val adjacentVertices = adjacencyList[from]
        if (adjacentVertices != null && adjacencyList.contains(to)) {
            adjacentVertices.add(to)
            return true
        }
        return false
    }

    fun getEdges(from: VertexType): Set<VertexType> {
        // Note: Elvis operator gives us a value if left hand expression is null
        return adjacencyList[from] ?: setOf()
    }

    fun clear() {
        adjacencyList.clear()
    }

    fun bfs(start: VertexType, target: VertexType): Boolean {
        val priorityList = queueList<VertexType>()
        val toVisit: MutableSet<VertexType> = mutableSetOf()

        priorityList.enqueue(start)
        toVisit.add(start)

        while (!priorityList.isEmpty()){
            val n = priorityList.dequeue() !!
            if (n == target) {
                return true
            }

            for (m in getEdges(n)){
                if (!toVisit.contains(m)){
                    priorityList.enqueue(m)
                    toVisit.add(m)
                }
            }
        }

        return false
    }


    fun dfs(start: VertexType, target: VertexType): Boolean{
        val priorityList = stackList<VertexType>()

        val toVisit: MutableSet<VertexType> = mutableSetOf()

        priorityList.push(start)
        toVisit.add(start)

        while (!priorityList.isEmpty()){
            val n = priorityList.pop() !!
            if (n == target) {
                return true
            }

            for (m in getEdges(n)){
                if (!toVisit.contains(m)){
                    priorityList.push(m)
                    toVisit.add(m)
                }
            }
        }

        return false
    }


}