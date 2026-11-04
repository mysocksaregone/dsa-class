package org.example

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 */
interface AssociativeArray<K, V> {
    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    operator fun set(k: K, v: V)

    /**
     * @return true if [k] is a key in the associative array
     */
    operator fun contains(k: K): Boolean

    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    operator fun get(k: K): V?

    /**
     * Remove the key, [k], from the associative array
     * @param k the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    fun remove(k: K): Boolean

    /**
     * @return the number of elements stored in the hash table
     */
    fun size(): Int

    /**
     * @return the full list of key value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K, V>>
}

class LinkedHashTable<K,V> : AssociativeArray<K,V> {


    // list of primes, provided in assignment
    // a list of values allows us to rehash with a differently selected m
    private val primes = listOf(53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317)
    private var currentPrime = 0

    // because of separate chaining, each bucket points to an association list
    // buckets is therefore an array of size currentPrime consisting of entirely mutable lists
    // each entry of these mutable lists then would have to be the pair of both key and value
    // use pair
    private var buckets = Array(primes[currentPrime]) {mutableListOf<Pair<K,V>>()}

    // the number of items stored currently
    private var numEntries = 0

    // hash
    private fun hash(k:K): Int {

        var h = 0
        // manual string conversion to int by turning each character to ASCII val
        // multiplied to 31 to shift bits around depending on order.
        // if i didn't do this, then strings with the same characters would
        // have the same value
        for (c in k.toString()) {
            h = h * 31 + c.code
        }

        return h.mod(primes[currentPrime])

    }

    // finding an entry
    private fun findEntry(k: K) : Pair<K, V>? {
        // hash the index
        // find the first value in that index's list
        return buckets[hash(k)].firstOrNull {it.first == k}
    }

    private fun rehash() {
        // first check if we've made it to the end of our predetermined
        // primes list... if so just give up
        if (currentPrime >= primes.size - 1) return

        currentPrime++

        // make a new empty array with new num of buckets
        val newBuckets = Array(primes[currentPrime]) {mutableListOf<Pair<K,V>>()}

        // copy over the old value of buckets to newBuckets
        for (list in buckets) {
            for (pair in list) {
                newBuckets[hash(pair.first)].add(pair)
            }
        }

        buckets = newBuckets
    }

    // insert
    override operator fun set(k: K, v: V) {
        // check if it already exists first
        // if it does, just set the value to v

        // get list in each bucket
        val list = buckets[hash(k)]

        // does it exist?
        val existing = list.indexOfFirst {it.first == k}

        if (existing != -1) {
            list[existing] = Pair(k, v)
            return
        }

        // if there's nothing, the list still exists, so just add
        // the pair to the bucket list
        list.add(Pair(k,v))

        // a new element stored! update numEntries
        numEntries++

        // now check if this addition made it too crowded
        // if so, rehash
        if (numEntries > buckets.size) rehash()
    }

    // lookup (boolean)
    override fun contains(k: K): Boolean {
        return findEntry(k) != null
    }

    // lookup (VALUE)
    override operator fun get(k: K): V? {
        return findEntry(k)?.second
    }

    // deletion
    override fun remove(k: K): Boolean {
        // if we find it, nuke it and return whether or not it got nuked
        val removed = buckets[hash(k)].removeAll {it.first == k}

        // one less entry
        if (removed) {
            numEntries--
        }

        // t or f
        return removed
    }


    override fun size(): Int {
        return numEntries
    }

    override fun keyValuePairs(): List<Pair<K, V>> {
        // returns a list of all values
        return buckets.flatMap { it }
    }

}