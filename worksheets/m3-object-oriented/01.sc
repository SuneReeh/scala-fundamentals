
import scala.language.`3.0-migration`

/**
  * m3-object-oriented -> 01.sc
  */
class Employee {
  var first: String = ""
  var last: String = ""
}

var amyJones = new Employee
amyJones.first = "Amy"
amyJones.last = "Jones"

println(amyJones.hashCode().toHexString)

var bobMartin = new Employee
bobMartin.first = "Bob"
bobMartin.last = "Martin"

println(bobMartin.hashCode().toHexString)

var taylorJackson = new Employee
taylorJackson.first = "Taylor"
taylorJackson.last = "Jackson"