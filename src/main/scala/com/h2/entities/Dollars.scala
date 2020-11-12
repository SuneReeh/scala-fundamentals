package com.h2.entities

class Dollars(val amount: Int) extends AnyVal {
  def +(dollars: Dollars): Dollars = Dollars(amount + dollars.amount)
  
  def -(dollars: Dollars): Dollars = Dollars(amount - dollars.amount)
  
  def >(dollars: Dollars): Boolean = amount > dollars.amount
  def >=(dollars: Dollars): Boolean = amount >= dollars.amount
  def <(dollars: Dollars): Boolean = amount > dollars.amount
  def <=(dollars: Dollars): Boolean = amount >= dollars.amount

  override def toString: String = "$" + amount
}

object Dollars {
  def apply(amount: Int): Dollars = new Dollars(amount)
}
