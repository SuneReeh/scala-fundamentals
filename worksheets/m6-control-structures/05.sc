/* Make the function a curried form */
def time[A](operation: A => Unit)(input: A): Unit = {
  val startTime = System.currentTimeMillis()
  operation(input)
  val elapsedTime = System.currentTimeMillis() - startTime
  println(s"======= took $elapsedTime milliseconds =======")
}

val operation = (n: Int) => {
  Thread.sleep(1000) // introduced latency
  val numbers = (1 to n).toList
  println(s"Sum of first $n numbers is ${numbers.sum}")
}

// call the curried function
time (operation)(100)

/* Replace the parenthesis with curly braces */
time {operation}(100)

/* Implement the function literal in curly braces */
time { (n: Int) =>
  val numbers = (1 to n).toList
  println(s"Sum of first $n numbers is ${numbers.sum}")
}(1000000)