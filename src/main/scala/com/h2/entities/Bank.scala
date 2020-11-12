package com.h2.entities

import java.time.LocalDate
import java.util.UUID

class Bank(val name: String,
           val city: String,
           val country: String,
           val email: Email) {
  private var depositProducts: Map[UUID, Deposits] = Map.empty
  private var depositAccounts: Map[UUID, DepositsAccount] = Map.empty
  private var lendingProducts: Map[UUID, Lending] = Map.empty
  private var lendingAccounts: Map[UUID, LendingAccount] = Map.empty
  private var customers: Map[UUID, Customer] = Map.empty
  
  println(s"$name Established 2018.")

  /**
    * // todo: (challenge?) how to disallow customer with same details?
    *
    * @param first       : first name for the customer
    * @param last        : last name for the customer
    * @param email       : email for the customer in 'value@domain' format
    * @param dateOfBirth : : date of birth for the customer in 'yyyy/mm/dd' format
    * @return the customer id for the new customer
    */
  def createNewCustomer(first: String, last: String, email: String, dateOfBirth: String): UUID = {
    def getEmail: Email =
      val Array(value, domain) = email.split("@")
      Email(value,domain)
    
    def getDateOfBirth: LocalDate =
      val Array(year, month, day) = dateOfBirth.split("/")
      LocalDate.of(year.toInt, month.toInt, day.toInt)
    
    val customer = new Customer(first, last, getEmail, getDateOfBirth)
    customers += (customer.id -> customer)
    customer.id
  }

  /**
    * // todo: (challenge?) how to disallow products of same name?
    *
    * @param name                        : name of the product
    * @param minBalance                  : the minimum balance required for the product
    * @param ratePerYear                 : the rate of interest earned by the end of the year
    * @param transactionsAllowedPerMonth : number of free transactions allowed for the product (optional)
    * @return the product id for the new product
    */
  def addNewDepositProduct(name: String, minBalance: Int, ratePerYear: Double, transactionsAllowedPerMonth: Int = 2): UUID = {
    val product = name match {
      case "CoreChecking" => new CoreChecking(Dollars(minBalance), ratePerYear)
      case "StudentCheckings" => new StudentCheckings(Dollars(minBalance), ratePerYear)
      case "RewardsSavings" => new RewardsSavings(Dollars(minBalance),ratePerYear,transactionsAllowedPerMonth)
    }
    
    depositProducts += (product.id -> product)
    product.id
  }

  /**
    * // todo: (challenge?) how to disallow products of same name?
    *
    * @param name                        : name of the product
    * @param annualFee                   : the annual fee for the product
    * @param apr                         : the apr for the product
    * @param rewardsPercent              : the rewards percentage earned upon using the product for payments
    * @return the product id for the new product
    */
  def addNewLendingProduct(name: String, annualFee: Double, apr: Double, rewardsPercent: Double): UUID = {
    val product = name match {
      case "CreditCard" => new CreditCard(annualFee, apr, rewardsPercent)
    }

    lendingProducts += (product.id -> product)
    product.id
  }

  def openDepositAccount(customerID: UUID, productID: UUID, amount: Dollars = Dollars(0)): UUID = {
    require(customers.get(customerID).nonEmpty, s"no customer found with id=$customerID")
    require(depositProducts.get(productID).nonEmpty, s"no deposits product found with id=$productID")

    val account = new DepositsAccount(customers(customerID),depositProducts(productID), amount)
    depositAccounts += (account.id -> account)
    account.id
  }

  def openLendingAccount(customerID: UUID, productID: UUID, balance: Dollars = Dollars(0)): UUID = {
    require(customers.get(customerID).nonEmpty, s"no customer found with id=$customerID")
    require(lendingProducts.get(productID).nonEmpty, s"no lending product found with id=$productID")

    val account = new LendingAccount(customers(customerID),lendingProducts(productID), balance)
    lendingAccounts += (account.id -> account)
    account.id
  }
  
  def deposit(accountID: UUID, dollars: Dollars): Unit = {
    require(depositAccounts.get(accountID).nonEmpty, "A valid deposits account Id must be provided")
    depositAccounts(accountID).deposit(dollars)
  }
  
  def withdraw(accountID: UUID, dollars: Dollars): Unit = {
    require(depositAccounts.get(accountID).nonEmpty, "A valid deposits account Id must be provided")
    depositAccounts(accountID).withdraw(dollars)
  }
  
  def useCreditCard(accountID: UUID, dollars: Dollars): Unit = {
    require(lendingAccounts.get(accountID).nonEmpty, "A valid lending account Id must be provided")
    lendingAccounts(accountID).withdraw(dollars)
  }
  
  def payCreditCardBill(accountID: UUID, dollars: Dollars): Unit = {
    require(lendingAccounts.get(accountID).nonEmpty, "A valid lending account Id must be provided")
    lendingAccounts(accountID).payBill(dollars)
  }

  override def toString: String = s"[$name]" +
    s" - ${customers.size} customers" +
    s" - ${depositProducts.size} deposits products" +
    s" - ${depositAccounts.size} deposits accounts" +
    s" - ${lendingProducts.size} lending products" +
    s" - ${lendingAccounts.size} lending accounts"
}
