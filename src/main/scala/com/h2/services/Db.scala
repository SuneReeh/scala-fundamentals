package com.h2.services

import java.util.UUID

import com.h2.entities._

trait CustomerDb {
  private var customers: Map[UUID, Customer] = Map.empty
  def saveCustomer(customer: Customer): Unit = customers += (customer.id -> customer)
  def getCustomer(id: UUID): Option[Customer] = customers.get(id)
  def numCustomers: Int = customers.size
}

trait ProductDb {
  private var depositProducts: Map[UUID, Deposit] = Map.empty
  private var lendingProducts: Map[UUID, Lending] = Map.empty

  def saveDepositProduct(product: Deposit): Unit = depositProducts += (product.id -> product)
  def saveLendingProduct(product: Lending): Unit = lendingProducts += (product.id -> product)
  def getDepositProduct(id: UUID): Option[Deposit] = depositProducts.get(id)
  def getLendingProduct(id: UUID): Option[Lending] = lendingProducts.get(id)
  def numDepositsProducts: Int = depositProducts.size
  def numLendingProducts: Int = lendingProducts.size
}

trait AccountDb {
  private var depositAccounts: Map[UUID, DepositAccount] = Map.empty
  private var lendingAccounts: Map[UUID, LendingAccount] = Map.empty

  def saveDepositAccount(account: DepositAccount): Unit = depositAccounts += (account.id -> account)
  def saveLendingAccount(account: LendingAccount): Unit = lendingAccounts += (account.id -> account)
  def getDepositAccount(id: UUID): Option[DepositAccount] = depositAccounts.get(id)
  def getLendingAccount(id: UUID): Option[LendingAccount] = lendingAccounts.get(id)
  def numDepositsAccounts: Int = depositAccounts.size
  def numLendingAccounts: Int = lendingAccounts.size
}