package com.h2.entities

import java.time.LocalDate
import java.util.UUID

class Customer(val first: String, val last: String,
               val email: Email, val dateOfBirth: LocalDate) extends Equals {
  val id: UUID = UUID.randomUUID()
  override def toString: String = s"$id: $last, $first => $email"

  override def canEqual(other: Any): Boolean = other.isInstanceOf[Customer]

  override def equals(other: Any): Boolean = other match {
    case that: Customer =>
      (that.canEqual(this)) &&
        first == that.first &&
        last == that.last &&
        email == that.email &&
        dateOfBirth == that.dateOfBirth
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(first, last, email, dateOfBirth)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
