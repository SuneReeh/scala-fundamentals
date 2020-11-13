package com.h2.entities

import java.util.UUID

trait Account {
  val id: UUID = UUID.randomUUID()
  val customer: Customer
  val product: Product

  def getBalance: Dollars
  
  override def toString = s"$customer with $product has a balance of $getBalance"
}

class DepositAccount(val customer: Customer,
                     val product: Deposit,
                     private var balance: Dollars) extends Account {

  def deposit(dollars: Dollars): Unit = {
    require(dollars > Dollars.Zero, "Amount deposited should be greater than zero.")
    balance += dollars
    println(s"Deposited $dollars to $this.")
  }

  def withdraw(dollars: Dollars): Unit = {
    require(dollars > Dollars.Zero && balance >= dollars, "Amount withdrawn should be greater than zero and less than or equal to balance.")
    balance -= dollars
    println(s"Withdrawn $dollars from $this.")
  }

  override def getBalance: Dollars = balance
}

class LendingAccount(val customer: Customer,
                     val product: Lending,
                     private var balance: Dollars) extends Account {

  def payBill(dollars: Dollars): Unit = {
    require(dollars > Dollars.Zero, "The payment must be made for amount greater than zero.")
    balance += dollars
    println(s"Paid bill of $dollars against $this.")
  }

  def withdraw(dollars: Dollars): Unit = {
    require(dollars > Dollars.Zero, "The withdrawal amount must be greater than zero.")
    balance -= dollars
    println(s"Debiting $dollars from $this.")
  }

  override def getBalance: Dollars = balance
}
