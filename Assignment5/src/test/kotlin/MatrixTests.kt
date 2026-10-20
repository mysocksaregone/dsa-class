package org.example
import kotlin.math.abs
import kotlin.test.Test


class MatrixTests {

    @Test
    private fun assertSameMatrix(a: Matrix, b: Matrix) {
        for (i in 0 until a.size) {
            for (j in 0 until a.size) {
                check (abs(a.getElement(i, j) - b.getElement(i, j)) < 1e-7) {
                    "Incorrect value at row i and column j."
                }
            }
        }
    }

    @Test
    fun makeTestMatrix(size: Int, offset: Int = 0) : Matrix {
        val result = Matrix(size)

        // keeping numbers single digit just to lessen complexity
        // optional offset to make the matrix a bit different
        for (r in 0 until size) {
            for (c in 0 until size) {
                result.setElement(r, c, ((r * 3 + c * 2 + offset) % 10).toDouble())
            }
        }

        return result
    }

    @Test
    fun testProduct()
    {
        val a = Matrix(2)
        val b = Matrix(2)
        a.setElement(0, 0, 1.0)
        a.setElement(0, 1, 2.0)
        a.setElement(1, 0, 3.0)
        a.setElement(1, 1, 4.0)

        b.setElement(0, 0, 5.0)
        b.setElement(0,1,6.0)
        b.setElement(1,0, 7.0)
        b.setElement(1,1, 8.0)

        val expected = Matrix(2)
        expected.setElement(0, 0, 19.0)
        expected.setElement(0, 1, 22.0)
        expected.setElement(1, 0, 43.0)
        expected.setElement(1, 1, 50.0)

        assertSameMatrix(expected,a.multiply(b)!!)
        assertSameMatrix(expected,a.strassenMultiply(b)!!)
        assertSameMatrix(expected, a.hybridMultiply(b, 2)!!)

    }

    @Test
    fun testQuadrants() {
        val matrix = Matrix(4)
        for (r in 0 until 4) {
            for (c in 0 until 4) {
                matrix.setElement(r, c, (r * 4 + c). toDouble())
            }
        }

        val (tl, tr, bl, br) = matrix.split()

        check(tl.getElement(0,0) == 0.0)
        check(tr.getElement(0,0) == 2.0)
        check(bl.getElement(0,0) == 8.0)
        check(br.getElement(1, 1) == 15.0)

    }

    @Test
    fun testArithmetic() {
        val a = makeTestMatrix(3)
        val b = makeTestMatrix(3, 2)

        assertSameMatrix(a.add(b).subtract(b), a)

    }

    @Test
    fun testIrregularSizes() {
        for (n in listOf(1, 3, 7, 9, 11, 25)){
            val a = makeTestMatrix(n)
            val b = makeTestMatrix(n, 3)

            val expected = a.multiply(b)!!

            assertSameMatrix(expected, a.strassenMultiply(b)!!)
            assertSameMatrix(expected, a.hybridMultiply(b, 4)!!)
        }
    }

    @Test
    fun testIncompatibleSizes() {
        val a = Matrix(2)
        val b = Matrix(3)

        check(a.multiply(b) == null)
        check(a.strassenMultiply(b) == null)
        check(a.hybridMultiply(b, 2) == null)
    }

}

