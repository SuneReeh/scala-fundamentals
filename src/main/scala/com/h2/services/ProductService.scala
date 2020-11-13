package com.h2.services

import java.util.UUID

import com.h2.entities._

trait ProductService extends ProductDb {

  /**
    * @param name                        : name of the product
    * @param minBalance                  : the minimum balance required for the product
    * @param ratePerYear                 : the rate of interest earned by the end of the year
    * @param transactionsAllowedPerMonth : number of free transactions allowed for the product (optional)
    * @return the product id for the new product
    */
  def addNewDepositProduct(name: String, minBalance: Int, ratePerYear: Double, transactionsAllowedPerMonth: Int = 2): UUID = {
    val product = name match {
      case "CoreChecking" => new CoreChecking(Dollars(minBalance), ratePerYear)
      case "StudentChecking" => new StudentChecking(Dollars(minBalance), ratePerYear)
      case "RewardsSavings" => new RewardsSavings(Dollars(minBalance), ratePerYear, transactionsAllowedPerMonth)
    }
    saveDepositProduct(product)
    product.id
  }

  /**
    * @param name           : name of the product
    * @param annualFee      : the annual fee for the product
    * @param apr            : the apr for the product
    * @param rewardsPercent : the rewards percentage earned upon using the product for payments
    * @return the product id for the new product
    */
  def addNewLendingProduct(name: String, annualFee: Double, apr: Double, rewardsPercent: Double): UUID = {
    val product = name match {
      case "CreditCard" => new CreditCard(annualFee, apr, rewardsPercent)
    }
    saveLendingProduct(product)
    product.id
  }
}
