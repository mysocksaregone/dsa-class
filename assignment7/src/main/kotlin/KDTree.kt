package org.example


/**
 * Computes the squared distance between two points.
 *
 * The square root is skipped because it doesn't change which point is closest,
 * and leaving it out makes the calculation faster.
 *
 * @param a the first point
 * @param b the second point (must have the same number of dimensions as a)
 * @return the squared distance between a and b
 */
fun squaredDistance(a: List<Double>, b: List<Double>): Double {
    var sum = 0.0
    for (i in a.indices) {
        val diff = a[i] - b[i]
        sum += diff * diff
    }

    return sum
}

// each node stores one point, the dimension it splits on, and its children
/**
 * One node of a KD tree.
 *
 * @property point the point stored at this node
 * @property dim the dimension this node splits on
 * @property left subtree of points with a smaller value than point on dim, or null if empty
 * @property right subtree of points with a larger value than point on dim, or null if empty
 */
class Node(val point: List<Double>, val dim: Int, val left: Node?, val right: Node?)

/**
 * A K-D tree for fast nearest neighbor search in k-dimensional space.
 *
 * The tree is built once from a list of points and can't be changed afterwards.
 *
 * @param points the points to store in the tree (can't be empty and all
 *   points must have the same number of dimensions)
 */
class KDTree (points: List<List<Double>>){
    // number of dimensions in each point
    private val k = points[0].size

    // building each node
    /**
     * Recursively builds a balanced (sub)tree from points.
     *
     * Splits on the dimension for this depth (cycling through the dimensions), uses the
     * median point along that dimension as the node, and builds the left and right
     * subtrees from the points before and after the median.
     *
     * @param points the points that belong in this subtree
     * @param depth how deep this subtree is in the tree (the root is depth 0)
     * @return the root node of the subtree, or null if points is empty
     */
    private fun build(points:List<List<Double>>, depth: Int): Node? {
        if (points.isEmpty()) return null

        // cycle through all the dimensions
        val dim = depth % k

        // sort by dimensions and use the middle point as splitting point
        val sorted = points.sortedBy {it[dim]}
        // middle index
        val mid = sorted.size / 2

        // instantiate node with middle value
        return Node(point = sorted[mid], dim = dim,
            left = build(sorted.subList(0, mid), depth + 1),
            right = build(sorted.subList(mid + 1, sorted.size), depth + 1))
    }

    private val root: Node? = build(points, 0)

   // used specifically to keep track of the current nearest value while searching
    private var bestPoint: List<Double>? = null
    private var bestDist: Double = 0.0

    /**
     * Recursively searches the subtree rooted at node, updating bestPoint
     * and bestDist whenever a closer point is found.
     *
     * Checks the current node's point, then searches the side of the split that
     * contains query. The other side is only searched if the splitting line
     * is closer to query than the best point found so far.
     *
     * @param node the root of the subtree to search (null means an empty subtree)
     * @param query the point we are looking for neighbors of
     */
    private fun search(node: Node?, query: List<Double>) {
        if (node == null) return

        // check the point in current node
        // if it's closer than best, new best
        val d = squaredDistance(node.point,query)
        if (d < bestDist) {
            bestDist = d
            bestPoint = node.point
        }

        // query val - current point val with respect to current dimension
        val diff = query[node.dim] - node.point[node.dim]

        // define subsequent dimensions
        val nearSide = if (diff < 0) node.left else node.right
        val farSide = if (diff < 0) node.right else node.left
        search(nearSide, query)

        // only check the other side if the current splitting line is closer than current best
        // if it's not, then there's no way the further side can have anything that meets criteria
        if (diff * diff < bestDist) {
            search(farSide, query)
        }
    }

    // overarching function that returns the nearest point
    /**
     * Finds the point in the tree that is closest to query.
     *
     * Uses the structure of the tree to skip regions that can't contain a closer
     * point, so it's usually faster than checking every point.
     *
     * @param query the point to search near (must have the same number of dimensions as the tree)
     * @return the point in the tree with the smallest squared distance to query
     */

    fun nearest(query: List<Double>) : List<Double> {
        bestPoint = null
        bestDist = Double.POSITIVE_INFINITY
        search(root,query)
        return bestPoint!!
    }

}