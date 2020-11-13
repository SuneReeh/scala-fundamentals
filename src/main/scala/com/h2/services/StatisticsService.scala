package com.h2.services

import com.h2.entities._

trait StatisticsService {
  def getTotalMoneyDeposited(accounts: Seq[Account]): Dollars = {
    accounts.foldLeft(Dollars.Zero){
      (total, account) => account.category match {
        case AccountCategory.DepositA => total + account.getBalance
        case _ => total
      }
    }
  }
  
  def getTotalMoneyBorrowed(accounts: Seq[Account]): Dollars = {
    accounts.filter(_.category == AccountCategory.LendingA).map(_.getBalance).reduce(_+_)
  }

  def getNumTransactionsByAccount(accounts: Seq[Account]): Map[String, Int] = {
    accounts.groupBy(_.category.toString).view.mapValues(_.map(_.transactions.length).sum).toMap
  }
}
