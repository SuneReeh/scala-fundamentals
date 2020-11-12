object Calculator {
  def sumOfSquares(a: Int, b: Int) = {
    def square(n: Int): Int = math.pow(n, 2).intValue()
    square(a) + square(b)
  }

  def multiplyDoubles(a: Int, b: Int) = {
    def double(n: Int): Int = {
      println(s"Parent received $a and $b")
      2 * n
    }
    double(a) * double(b)
  }

  def divisionOfCubes(a: Int, b: Int) = {
    def cube(n: Int): Int = math.pow(n, 3).intValue()
    cube(a) / cube(b)
  }
}


Calculator.sumOfSquares(2, 3)
Calculator.multiplyDoubles(3, 2)
Calculator.divisionOfCubes(5, 2)