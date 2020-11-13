package com.h2.entities

object Dollars {
  val Zero: Dollars = Dollars(0)

  def apply(amount: Int): Dollars = new Dollars(amount)
}

class Dollars(val amount: Int) extends AnyVal with Ordered[Dollars] {
  override def compare(that: Dollars): Int = amount - that.amount

  def +(dollars: Dollars): Dollars = Dollars(amount + dollars.amount)

  def -(dollars: Dollars): Dollars = Dollars(amount - dollars.amount)

  override def toString: String = "$" + amount
}
