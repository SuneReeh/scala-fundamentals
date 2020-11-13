package com.h2.services

import java.util.UUID

import com.h2.entities._

trait AccountService extends AccountDb with CustomerDb with ProductDb {

  def openDepositAccount(customerID: UUID, productID: UUID, amount: Dollars = Dollars.Zero): UUID = {
    val maybeCustomer = getCustomer(customerID)
    require(maybeCustomer.nonEmpty, s"no customer found with id=$customerID")
    val maybeProduct = getDepositProduct(productID)
    require(maybeProduct.nonEmpty, s"no deposits product found with id=$productID")

    val account = new DepositAccount(maybeCustomer.get, maybeProduct.get, amount)
    saveDepositAccount(account)
    account.id
  }

  def openLendingAccount(customerID: UUID, productID: UUID, balance: Dollars = Dollars.Zero): UUID = {
    val maybeCustomer = getCustomer(customerID)
    require(maybeCustomer.nonEmpty, s"no customer found with id=$customerID")
    val maybeProduct = getLendingProduct(productID)
    require(maybeProduct.nonEmpty, s"no lending product found with id=$productID")

    val account = new LendingAccount(maybeCustomer.get, maybeProduct.get, balance)
    saveLendingAccount(account)
    account.id
  }

  def deposit(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getDepositAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid deposits account ID must be provided")
    maybeAccount.get.deposit(dollars)
  }

  def withdraw(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getDepositAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid deposits account ID must be provided")
    maybeAccount.get.withdraw(dollars)
  }

  def useCreditCard(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getLendingAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid lending account ID must be provided")
    maybeAccount.get.withdraw(dollars)
  }

  def payCreditCardBill(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getLendingAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid lending account ID must be provided")
    maybeAccount.get.payBill(dollars)
  }
}
