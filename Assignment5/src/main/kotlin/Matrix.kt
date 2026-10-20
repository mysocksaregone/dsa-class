package org.example

class Matrix (val size: Int){
    // don't forget this is one dimension
    // access value [row, col] with row * size + col
    private val cells = DoubleArray(size * size)

    fun getElement(row: Int, col: Int) : Double {
        // require() throws an error if it turns out to be false
        // learning to be on top of safety ...
        require(row in 0 until size && col in 0 until size)
        return cells[row * size + col]
    }

    fun setElement(row: Int, col: Int, value: Double) {
        require(row in 0 until size && col in 0 until size)
        cells[row * size + col] = value
    }

    fun add(other: Matrix): Matrix  {
        // confirming that they're the same size
        require(size == other.size) {
            "Matrix sizes must be the same."
        }

        val result = Matrix(size)

        // adding cells
        for (i in cells.indices) {
            result.cells[i] = cells[i] + other.cells[i]
        }

        return result
    }

    fun subtract(other:Matrix): Matrix {
        require(size == other.size) {
            "Matrix sizes must be the same."
        }

        val result = Matrix(size)

        // subtracting cells
        for (i in cells.indices){
            result.cells[i] = cells[i] - other.cells[i]
        }

        return result
    }

    // divides matrix into four EQUAL submatrices
    fun split(): List<Matrix> {
        // matrix needs to be even to split
        require(size % 2 == 0 ) {
            "Matrix not even."
        }

        val half = size / 2

        // blocking matrix
        val topLeft = Matrix(half)
        val topRight = Matrix(half)
        val bottomLeft = Matrix(half)
        val bottomRight = Matrix(half)

        // setting values
        for (r in 0 until half) {
            for (c in 0 until half) {
                topLeft.setElement(r, c, getElement(r, c))
                topRight.setElement(r, c, getElement(r, c + half))
                bottomLeft.setElement(r, c, getElement(r+half, c))
                bottomRight.setElement(r, c, getElement(r + half, c+half))
            }
        }

        return listOf( topLeft, topRight, bottomLeft, bottomRight)
    }

    // standard matrix multiplication
    fun multiply(other:Matrix) : Matrix? {
        if (size != other.size) return null

        val result = Matrix(size)

        for (r in 0 until size) {
            for (k in 0 until size) {
                val leftValue = getElement(r,k )

                for (c in 0 until size) {
                    val newValue = result.getElement(r, c) + leftValue * other.getElement(k, c)
                    result.setElement(r, c, newValue)
                }
            }
        }

        return result
    }

    // uses Strassen until submatrix size reaches indicated cutoff

    // used to find the smallest power of two after given size
    private fun nextPowerOfTwo(value: Int): Int {
        var power = 1

        while (power < value) {
            power *= 2
        }

        return power
    }

    // uses strassen's until cutoff, then uses standard multiplication
    private fun recursiveMultiply(a: Matrix, b: Matrix, cutoff: Int): Matrix {
        val n = a.size

        if (n <= cutoff) {
            // throws an error if product is null
            return a.multiply(b)!!
        }

        // defining matrices a and b
        val aParts = a.split()
        val bParts = b.split()

        // defining a matrix blocks
        val a11 = aParts[0]
        val a12 = aParts[1]
        val a21 = aParts[2]
        val a22 = aParts[3]

        // defining b matrix blocks
        val b11 = bParts[0]
        val b12 = bParts[1]
        val b21 = bParts[2]
        val b22 = bParts[3]

        // strassen's 7 products
        val m1 = recursiveMultiply(a11.add(a22), b11.add(b22), cutoff)
        val m2 = recursiveMultiply(a21.add(a22), b11, cutoff)
        val m3 = recursiveMultiply(a11, b12.subtract(b22), cutoff)
        val m4 = recursiveMultiply(a22, b21.subtract(b11), cutoff)
        val m5 = recursiveMultiply(a11. add(a12), b22, cutoff)
        val m6 = recursiveMultiply(a21.subtract(a11), b11.add(b12), cutoff)
        val m7 = recursiveMultiply(a12.subtract(a22), b21.add(b22), cutoff)

        // creating matrix c by combining 7 products
        val c11 = m1.add(m4).subtract(m5).add(m7)
        val c12 = m3.add(m5)
        val c21 = m2.add(m4)
        val c22 = m1.subtract(m2).add(m3).add(m6)

        val result = Matrix(n)
        val half = n/2

        for (r in 0 until half) {
            for (c in 0 until half) {
                result.setElement(r, c, c11.getElement(r,c))
                result.setElement(r, c + half, c12.getElement(r, c))
                result.setElement(r + half, c, c21.getElement(r, c))
                result.setElement(r + half, c + half, c22.getElement(r, c))
            }
        }

        return result
    }

    fun hybridMultiply(other: Matrix, cutoff:Int): Matrix? {
        // must be the same size!
        if (size!= other.size) return null

        require(cutoff>= 1) {
            "Cutoff is less than 1."
        }

        // pads matrices with 0s to reach power-of-2 size
        val paddedSize = nextPowerOfTwo(size)
        val a = Matrix(paddedSize)
        val b = Matrix(paddedSize)

        for (r in 0 until size) {
            for (c in 0 until size ) {
                a.setElement(r, c, getElement(r, c))
                b.setElement(r, c, other.getElement(r, c))
            }
        }

        val product = recursiveMultiply(a, b, cutoff)

        // remove padding
        val result = Matrix(size)

        for (r in 0 until size) {
            for (c in 0 until size) {
                result.setElement(r, c, product.getElement(r, c))
            }
        }

        return result
    }

    // run hybrid multiply with basically no cutoff
    fun strassenMultiply(other: Matrix) : Matrix? {
        return hybridMultiply(other, 1)
    }

    // formats the matrix
    override fun toString(): String {
        var result = ""

        for (r in 0 until size) {
            result += "["

            for (c in 0 until size) {
                result += getElement(r, c).toString()

                if (c < size - 1) {
                    result += ", "
                }
            }

            result += "]"

            if (r < size - 1) {
                result += "\n"
            }
        }

        return result
    }

}