package com.h2.entities

class Dollars(val amount: Int) extends AnyVal {
  def +(value: Int) = Dollars(amount + value)
  
  def -(value: Int) = Dollars(amount - value)
  
  def >(value: Int) = amount > value
  def >=(value: Int) = amount >= value
  def <(value: Int) = amount > value
  def <=(value: Int) = amount >= value

  override def toString(): String = "$" + amount
}

object Dollars {
  def apply(amount: Int): Dollars = new Dollars(amount)
}
