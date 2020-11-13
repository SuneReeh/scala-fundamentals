package com.h2.entities

class Email(val value: String, val domain: String) extends Equals {
  override def toString: String = s"$value@$domain"

  def canEqual(other: Any): Boolean = other.isInstanceOf[Email]
  
  override def equals(other: Any): Boolean = other match {
    case that: Email =>
      (that canEqual this) &&
        value == that.value &&
        domain == that.domain
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(value, domain)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Email {
  def apply(value: String, domain: String): Email = new Email(value, domain)
}
