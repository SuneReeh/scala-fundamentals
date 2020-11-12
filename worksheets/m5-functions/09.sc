import scala.annotation.tailrec

val n = 5

def sumR(n: Int): Int = {
  if(n == 1) 1
  else n + sumR(n-1)
}

sumR(n)


def sumTR(n: Int): Int = {
  @tailrec
  def go(currentNum: Int, totalSoFar: Int = 0): Int = {
    if(currentNum == 0) totalSoFar
    else go(currentNum - 1, totalSoFar + currentNum)
  }
  go(n)
}

sumTR(n)
sumTR(10)

case class GetSum() {

  def maybeTrueTailRecursion(i: Int): Int = {
    print(".")
    if(i==1) 1
    else maybeTrueTailRecursion(i - 1)
  }

  @tailrec
  final def trueTailRecursion(i: Int): Int = {
    print(".")
    if(i==1) 1
    else trueTailRecursion(i - 1)
  }

}

println("Testing long tail recursion:")
val i = 60000
//  val i = 1000

GetSum().trueTailRecursion(i)
println("\nCompleted")
//GetSum().maybeTrueTailRecursion(i)