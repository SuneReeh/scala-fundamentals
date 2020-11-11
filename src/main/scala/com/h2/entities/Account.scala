package com.h2.entities

trait Account {
  val customer: Customer
  val product: Product

  def getBalance: Dollars
  
  override def toString = s"$customer with $product has a balance of $getBalance"
}

class DepositsAccount(val customer: Customer,
                      val product: Deposits,
                      private var balance: Dollars) extends Account {

  def deposit(amount: Int): Unit = {
    require(amount > 0, "Amount deposited should be greater than zero.")
    println(s"Depositing $amount to $customer account.")
    balance += amount
  }

  def withdraw(amount: Int): Unit = {
    require(amount > 0 && balance >= amount, "Amount withdrawn should be greater than zero and less than or equal to balance.")
    println(s"Withdrawing $amount from $customer account.")
    balance -= amount
  }

  override def getBalance = balance
}

class LendingAccount(val customer: Customer,
                     val product: Lending,
                     private var balance: Dollars) extends Account {

  def payBill(amount: Int): Unit = {
    require(amount > 0, "The payment must be made for amount greater than zero.")
    println(s"Paying bill of $amount against $customer account.")
    balance += amount
  }

  def withdraw(amount: Int): Unit = {
    require(amount > 0, "The withdrawal amount must be greater than zero.")
    println(s"Debiting $amount from $customer account.")
    balance -= amount
  }

  override def getBalance = balance
}
