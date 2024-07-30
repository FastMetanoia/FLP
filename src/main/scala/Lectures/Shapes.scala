package Lectures


import scala.math.{Pi, pow, sqrt}

object Shapes {
  object OOP{
    trait Shape {
      def diameter: Double
      def area: Double
    }
    class Square(r:Double) extends Shape {
      override def diameter: Double = 2*sqrt(2)*r
      override def area: Double = pow(r*2,2)
    }
    class Circle(r:Double) extends Shape{
      override def diameter: Double = 2*r
      override def area: Double = 2*Pi*r*r
    }
  }

  object FP{
    case class Square(r:Double)
    case class Circle(r:Double)
    type Shape = Square|Circle
    def diameter(shape: Shape):Double = shape match {
      case Square(r) => 2*sqrt(2)*r
      case Circle(r) => 2*r
    }
    def area(shape: Shape):Double = shape match {
      case Square(r) => pow(r*2,2)
      case Circle(r) => 2*Pi*r*r
    }
  }
}
