package edu.datastructures

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
interface Shape {
    fun area(): Double
    fun perimeter(): Double
}

class Circle(private val radius: Double): Shape
{
    override fun area(): Double {
        return Math.PI * radius * radius
    }
    override fun perimeter(): Double {
        return 2 * Math.PI * radius
    }
}

class Rectangle(private val width: Double,
                private val height: Double): Shape {
    override fun area(): Double {
        return width * height
    }

    override fun perimeter(): Double {
        return 2*(width + height)
    }
}

fun totalAra (shapes: List<Shape>): Double {
    var total = 0.0
    for (shape in shapes) {
        total += shape.area()
    }
    return total
}

fun main() {
    // though it's smart enough to infer it's a list of shapes,
    // if you include a property that no longer makes it a shape
    // without defining it as a list of shapes, it WON'T give you
    // an error and instead give you a list of Any
    // (specific data type)
    val x: List<Shape> = listOf(Circle(radius = 3.0), Rectangle(4.0,5.0))

}